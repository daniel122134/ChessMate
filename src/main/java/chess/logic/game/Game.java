package chess.logic.game;

import chess.logic.pieces.Bishop;
import chess.logic.pieces.King;
import chess.logic.pieces.Pawn;
import chess.logic.pieces.Piece;
import chess.logic.pieces.Queen;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class Game {
    
    public Board board;
    public PlayerColors currentTurn;
    
    public Game() {
        Piece[][] matrix = {
            {new Bishop("black", new Point(0, 0)), new Bishop("black", new Point(1, 0)), new Bishop("black", new Point(2, 0)), new Queen("black", new Point(3, 0)),
             new King("black", new Point(4, 0)), new Bishop("black", new Point(5, 0)), new Bishop("black", new Point(6, 0)), new Bishop("black", new Point(7, 0))},
            {new Pawn("black", new Point(0, 1)), new Pawn("black", new Point(1, 1)), new Pawn("black", new Point(2, 1)), new Pawn("black", new Point(3, 1)),
             new Pawn("black", new Point(4, 1)), new Pawn("black", new Point(5, 1)), new Pawn("black", new Point(6, 1)), new Pawn("black", new Point(7, 1)),},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {new Pawn("white", new Point(0, 6)), new Pawn("white", new Point(1, 6)), new Pawn("white", new Point(2, 6)), new Pawn("white", new Point(3, 6)),
             new Pawn("white", new Point(4, 6)), new Pawn("white", new Point(5, 6)), new Pawn("white", new Point(6, 6)), new Pawn("white", new Point(7, 6))},
            {new Bishop("white", new Point(0, 7)), new Bishop("white", new Point(1, 7)), new Bishop("white", new Point(2, 7)), new Queen("white", new Point(3, 7)),
             new King("white", new Point(4, 7)), new Bishop("white", new Point(5, 7)), new Bishop("white", new Point(6, 7)), new Bishop("white", new Point(7, 7))}
            
        };
        this.board = new Board(matrix);
        this.currentTurn = PlayerColors.WHITE;
    }
    
    public static boolean isWin(Board board) {
        return false;
    }
    
    public ArrayList<Point> getMoves(Point location) {
        ArrayList<Point> points = new ArrayList<Point>();
        Piece piece = this.board.getPieceAtLocation(location);
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
                        break;
                    }
                    Piece tempPiece = this.board.getPieceAtLocation(temp);
                    if (!move.getIsAllowed().run(step, tempPiece)) {
                        break;
                    }
                    
                    points.add(temp);
                    step += 1;
                    
                    if (move.getIsLast().run(step, tempPiece)) {
                        break;
                    }
                    
                }
            }
        }
        if (piece instanceof King) {
            points = (ArrayList<Point>) points.stream().filter(s -> {
                try {
                    Board tempBoard = new Board(this.board);
                    tempBoard._move(location, s);
                    return !isWin(tempBoard);
                } catch (Exception e) {
                    return false;
                }
            }).collect(Collectors.toList());
        }
        return points;
    }
    
    public void move(Point src, Point dst) throws Exception {
        if (!isMoveAllowed(src, dst)) {
            throw new Exception("Move not allowed");
        }
        this.board._move(src, dst);
        this.currentTurn = this.currentTurn == PlayerColors.WHITE ? PlayerColors.BLACK : PlayerColors.WHITE;
    }
    
    
    public boolean isMoveAllowed(Point src, Point dst) {
        Piece pieceToMove = this.board.getPieceAtLocation(src);
        if (!Objects.equals(pieceToMove.GetColor(), this.currentTurn.getColor())) {
            return false;
        }
        ArrayList<Point> allowed = getMoves(src);
        return allowed.contains(dst);
    }
    
    
}


