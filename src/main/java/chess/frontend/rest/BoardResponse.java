package chess.frontend.rest;

import chess.logic.pieces.Piece;

import java.util.HashMap;

public class BoardResponse {
    
    private final long id;
    private final Piece[][] matrix;
    
    public BoardResponse(long id, Piece[][] matrix) {
        this.id = id;
        this.matrix = matrix;
    }
    
    public long getId() {
        return id;
    }
    
    public HashMap<String, String>[][] getContent() {
        
        HashMap<String, String>[][] board = new HashMap[8][8];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = this.matrix[i][j] != null ? this.matrix[i][j].toJson() : new HashMap<>();
            }
        }
        return board;
    }
}
