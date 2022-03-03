package chess.logic.game;

import chess.logic.pieces.King;
import chess.logic.pieces.Piece;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Board {
    
    public Piece[][] matrix;
    public HashMap<PlayerColors, King> kings = new HashMap<>();
    
    public Board(Piece[][] matrix) {
        this.matrix = matrix;
        for (Piece[] row : this.matrix) {
            for (Piece p : row
            ) {
                if (p != null && p instanceof King) {
                    kings.put(p.getColor(), (King) p);
                }
            }
        }
    }
    
    public Board(Board board) {
        this.matrix = new Piece[board.matrix.length][board.matrix[0].length];
        for (int i = 0; i < board.matrix.length; i++) {
            for (int j = 0; j < board.matrix[i].length; j++) {
                this.matrix[i][j] = board.matrix[i][j] != null ? board.matrix[i][j].copy() : null;
            }
            
        }
        
        for (Piece[] row : this.matrix) {
            for (Piece p : row
            ) {
                if (p != null && p instanceof King) {
                    kings.put(p.getColor(), (King) p);
                }
            }
        }
    }
    
    public Piece getPieceAtLocation(Point location) {
        return this.matrix[location.y][location.x];
    }
    
    
    public void _move(Point src, Point dst) {
        Piece p = getPieceAtLocation(src);
        if (p != null) {
            placePiece(p, dst);
        }
    }
    
    public void placePiece(Piece p, Point dst) {
        this.matrix[p.getLocation().y][p.getLocation().x] = null;
        p.setLocation(dst);
        this.matrix[dst.y][dst.x] = p;
        
    }
    
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Piece[] row : this.matrix) {
            for (Piece p : row) {
                s.append(String.format("%s ", p == null ? "_" : p));
            }
            s.append("\n");
        }
        return s.toString();
    }
    
    
    public ArrayList<Piece> getAllLivePieces() {
        ArrayList<Piece> pieces = new ArrayList<>();
        for (Piece[] row : this.matrix) {
            for (Piece p : row) {
                if (p != null) {
                    pieces.add(p);
                }
            }
        }
        return pieces;
    }
    
    public ArrayList<Piece> getAllLivePiecesForColor(PlayerColors color) {
        ArrayList<Piece> pieces = getAllLivePieces();
        return (ArrayList<Piece>) pieces.stream().filter(s -> s.getColor() == color).collect(Collectors.toList());
    }
    
    public Board getBoardAfterMove(Point src, Point dst) {
        Board tempBoard = new Board(this);
        tempBoard._move(src, dst);
        return tempBoard;
        
    }
    
    public ArrayList<Point> getMoves(Point location) {
        ArrayList<Point> points = new ArrayList<>();
        Piece piece = this.getPieceAtLocation(location);
        if (piece == null) {
            return points;
        }
        Move[] moves = piece.getMoves();
        for (Move move : moves) {
            for (int[] dir : move.getDirs()) {
                int step = 1;
                
                while (true) {
                    
                    Point temp = new Point(location.x + (dir[0] * step), location.y + (dir[1] * step));
                    if (temp.x > 7 || temp.x < 0 || temp.y < 0 || temp.y > 7) {
                        //board overflow
                        break;
                    }
                    Piece tempPiece = this.getPieceAtLocation(temp);
                    if (!move.getIsAllowed().run(step, tempPiece)) {
                        // lands on the same color piece
                        break;
                    }
                    
                    points.add(temp);
                    
                    if (move.getIsLast().run(step, tempPiece)) {
                        break;
                    }
                    
                    step += 1;
                    
                }
            }
        }
        return points;
    }
    
    public ArrayList<Point> getMovesFiltered(Point location) {
        ArrayList<Point> points = new ArrayList<>();
        Piece piece = this.getPieceAtLocation(location);
        if (piece == null) {
            return points;
        }
        points = getMoves(location);
        
        points = (ArrayList<Point>) points.stream().filter(s -> {
            try {
                Board tempBoard = this.getBoardAfterMove(location, s);
                return !isCheck(tempBoard, tempBoard.kings.get(piece.getColor()));
            } catch (Exception e) {
                return false;
            }
        }).collect(Collectors.toList());
        
        return points;
    }
    
    
    public static boolean isCheck(Board board, King king) {
        // Is there any legal move. check if king under threat.
        Point kingLoc = king.getLocation();
        ArrayList<Piece> enemyPieces = board.getAllLivePiecesForColor(king.getColor().getOppositeColor());
        for (Piece p : enemyPieces) {
            if (board.getMoves(p.getLocation()).contains(kingLoc)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isMate(Board board, Piece p) {
        return true;
    }
    
    
    public double getRank(PlayerColors color) {
        // add pawn influene
        // king defence
        // obtain castling
        // re calculate top 1% if more then 1 option exists - only do this one time
        //https://chessfox.com/example-of-the-complete-evaluation-process-of-chess-a-chess-position/
        ArrayList<Piece> piecesOnBoard = this.getAllLivePieces();
        HashMap<PlayerColors, Double> scores = new HashMap<>();
        double whiteScore = 0.;
        double blackScore = 0.;
        for (Piece p : piecesOnBoard) {
            switch (p.getColor()) {
                case BLACK:
                    blackScore += p.getWorth();
                    blackScore += this.getMoves(p.getLocation()).size() * 0.1;
                    break;
                case WHITE:
                    whiteScore += p.getWorth();
                    whiteScore += this.getMoves(p.getLocation()).size() * 0.1;
                    break;
            }
        }
        
        scores.put(PlayerColors.WHITE, whiteScore);
        scores.put(PlayerColors.BLACK, blackScore);
        return scores.get(color) / scores.get(color.getOppositeColor());
    }
}
