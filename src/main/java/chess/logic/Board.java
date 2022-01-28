package chess.logic;

import chess.logic.pieces.Bishop;
import chess.logic.pieces.King;
import chess.logic.pieces.Pawn;
import chess.logic.pieces.Piece;
import chess.logic.pieces.Queen;

import java.awt.Point;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Board {
    
    Piece[][] matrix = {
        {new Bishop("white", new Point(0, 0)), new Bishop("white", new Point(1, 0)), new Bishop("white", new Point(2, 0)), new Queen("white", new Point(3, 0)),
         new King("white", new Point(4, 0)), new Bishop("white", new Point(5, 0)), new Bishop("white", new Point(6, 0)), new Bishop("white", new Point(7, 0))},
        {new Pawn("white", new Point(0, 1)), new Pawn("white", new Point(1, 1)), new Pawn("white", new Point(2, 1)), new Pawn("white", new Point(3, 1)),
         new Pawn("white", new Point(4, 1)), new Pawn("white", new Point(5, 1)), new Pawn("white", new Point(6, 1)), new Pawn("white", new Point(7, 1)),},
        {null, null, null, null, null, null, null, null},
        {null, null, null, null, null, null, null, null},
        {null, null, null, null, null, null, null, null},
        {null, null, null, null, null, null, null, null},
        {new Pawn("black", new Point(0, 6)), new Pawn("black", new Point(1, 6)), new Pawn("black", new Point(2, 6)), new Pawn("black", new Point(3, 6)),
         new Pawn("black", new Point(4, 6)), new Pawn("black", new Point(5, 6)), new Pawn("black", new Point(6, 6)), new Pawn("black", new Point(7, 6))},
        {new Bishop("black", new Point(0, 7)), new Bishop("black", new Point(1, 7)), new Bishop("black", new Point(2, 7)), new Queen("black", new Point(3, 7)),
         new King("black", new Point(4, 7)), new Bishop("black", new Point(5, 7)), new Bishop("black", new Point(6, 7)), new Bishop("black", new Point(7, 7))}
        
    };
    
    public Board() {
        
    }
    
    public Board(Board board) {
        this.matrix = board.matrix.clone();
    }
    
    public ArrayList<Point> getMoves(Point location) {
        ArrayList<Point> points = new ArrayList<Point>();
        Piece piece = this.matrix[location.y][location.x];
        if (piece == null){
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
                    
                    if (!move.getIsAllowed().run(step, this.matrix[temp.y][temp.x])) {
                        break;
                    }
                    
                    points.add(temp);
                    step += 1;
                    
                    if (move.getIsLast().run(step, this.matrix[temp.y][temp.x])) {
                        break;
                    }
                    
                }
            }
        }
        if (piece instanceof King) {
            points = (ArrayList<Point>) points.stream().filter(s -> {
                try {
                    Board tempBoard = new Board(this);
                    tempBoard._move(location, s);
                    return !isWin(tempBoard);
                } catch (Exception e) {
                    return false;
                }
            }).collect(Collectors.toList());
        }
        return points;
    }
    
    public static boolean isWin(Board board) {
        return false;
    }
    
    public void move(Point src, Point dst) throws Exception {
        if (!isMoveAllowed(src, dst)) {
            throw new Exception("Move not allowed");
        }
        _move(src, dst);
    }
    
    public void _move(Point src, Point dst) {
        this.matrix[dst.y][dst.x] = this.matrix[src.y][src.x];
        this.matrix[src.y][src.x] = null;
    }
    
    public boolean isMoveAllowed(Point src, Point dst) {
        ArrayList<Point> allowed = getMoves(src);
        return allowed.contains(dst);
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
    
    
}