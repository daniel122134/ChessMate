package chess.logic.game;

import chess.logic.pieces.Piece;

import java.awt.Point;

public class Board {
    
    public Piece[][] matrix;
    
    public Board(Piece[][] matrix) {
        this.matrix = matrix;
    }
    
    public Board(Board board) {
        this.matrix = new Piece[board.matrix.length][board.matrix[0].length];
        for (int i = 0; i < board.matrix.length; i++) {
            for (int j = 0; j < board.matrix[i].length; j++) {
                this.matrix[i][j] = board.matrix[i][j];
    
            }
    
        }
    }
    
    public Piece getPieceAtLocation(Point location) {
        return this.matrix[location.y][location.x];
    }
    
    
    public void _move(Point src, Point dst) {
        this.matrix[dst.y][dst.x] = this.matrix[src.y][src.x];
        this.matrix[src.y][src.x] = null;
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
