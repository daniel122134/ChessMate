package chess.logic.game;

import chess.logic.pieces.Piece;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Collectors;

public class Board {
    
    public Piece[][] matrix;
    
    public Board(Piece[][] matrix) {
        this.matrix = matrix;
    }
    
    public Board(Board board) {
        this.matrix = new Piece[board.matrix.length][board.matrix[0].length];
        for (int i = 0; i < board.matrix.length; i++) {
            for (int j = 0; j < board.matrix[i].length; j++) {
                this.matrix[i][j] = board.matrix[i][j] != null ? board.matrix[i][j].copy() : null;
            }
    
        }
    }
    
    public Piece getPieceAtLocation(Point location) {
        return this.matrix[location.y][location.x];
    }
    
    
    public void _move(Point src, Point dst) {
        Piece p  = this.matrix[src.y][src.x];
        if (p!=null){
            p.setLocation(dst);
        }
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
    
    
    public ArrayList<Piece> getAllLivePieces() {
        ArrayList<Piece> pieces = new ArrayList<>();
        for (Piece[] row : this.matrix) {
            for (Piece p : row) {
                if (p!=null){
                    pieces.add(p);
                }
            }
        }
        return pieces;
    }
    
    public ArrayList<Piece> getAllLivePiecesForColor(PlayerColors color) {
        ArrayList<Piece> pieces = new ArrayList<>();
        for (Piece[] row : this.matrix) {
            for (Piece p : row) {
                if (p!=null){
                    pieces.add(p);
                }
            }
        }
        return (ArrayList<Piece>) pieces.stream().filter(s -> {return s.getColor() == color;}).collect(Collectors.toList());
    }
    
    public Board getBoardAfterMove(Point src, Point dst){
        Board tempBoard = new Board(this);
        tempBoard._move(src, dst);
        return tempBoard;
        
    }
    
    public double getRank(PlayerColors color){
        ArrayList<Piece> piecesOnBoard = this.getAllLivePieces();
        HashMap<PlayerColors,Double> scores = new HashMap<>();
        scores.put(PlayerColors.WHITE,0.);
        scores.put(PlayerColors.BLACK,0.);
        for (Piece p : piecesOnBoard) {
            scores.put(p.getColor(), scores.get(p.getColor()) + p.getWorth());
        }
        
        return scores.get(color) / scores.get(Objects.equals(color, PlayerColors.WHITE) ? PlayerColors.BLACK : PlayerColors.WHITE);
    }
}
