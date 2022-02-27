package chess.logic.game;

import chess.logic.pieces.Bishop;
import chess.logic.pieces.King;
import chess.logic.pieces.Pawn;
import chess.logic.pieces.Piece;
import chess.logic.pieces.Queen;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Collectors;

public class Game {
    
    public Board board;
    public PlayerColors currentTurn;
    
    public Game() {
        Piece[][] matrix = {
            {new Bishop(PlayerColors.BLACK, new Point(0, 0)), new Bishop(PlayerColors.BLACK, new Point(1, 0)), new Bishop(PlayerColors.BLACK, new Point(2, 0)), new Queen(PlayerColors.BLACK, new Point(3, 0)),
             new King(PlayerColors.BLACK, new Point(4, 0)), new Bishop(PlayerColors.BLACK, new Point(5, 0)), new Bishop(PlayerColors.BLACK, new Point(6, 0)), new Bishop(PlayerColors.BLACK, new Point(7, 0))},
            {new Pawn(PlayerColors.BLACK, new Point(0, 1)), new Pawn(PlayerColors.BLACK, new Point(1, 1)), new Pawn(PlayerColors.BLACK, new Point(2, 1)), new Pawn(PlayerColors.BLACK, new Point(3, 1)),
             new Pawn(PlayerColors.BLACK, new Point(4, 1)), new Pawn(PlayerColors.BLACK, new Point(5, 1)), new Pawn(PlayerColors.BLACK, new Point(6, 1)), new Pawn(PlayerColors.BLACK, new Point(7, 1)),},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {new Pawn(PlayerColors.WHITE, new Point(0, 6)), new Pawn(PlayerColors.WHITE, new Point(1, 6)), new Pawn(PlayerColors.WHITE, new Point(2, 6)), new Pawn(PlayerColors.WHITE, new Point(3, 6)),
             new Pawn(PlayerColors.WHITE, new Point(4, 6)), new Pawn(PlayerColors.WHITE, new Point(5, 6)), new Pawn(PlayerColors.WHITE, new Point(6, 6)), new Pawn(PlayerColors.WHITE, new Point(7, 6))},
            {new Bishop(PlayerColors.WHITE, new Point(0, 7)), new Bishop(PlayerColors.WHITE, new Point(1, 7)), new Bishop(PlayerColors.WHITE, new Point(2, 7)), new Queen(PlayerColors.WHITE, new Point(3, 7)),
             new King(PlayerColors.WHITE, new Point(4, 7)), new Bishop(PlayerColors.WHITE, new Point(5, 7)), new Bishop(PlayerColors.WHITE, new Point(6, 7)), new Bishop(PlayerColors.WHITE, new Point(7, 7))}
            
        };
        this.board = new Board(matrix);
        this.currentTurn = PlayerColors.WHITE;
    }
    
  

    
    public void move(Point src, Point dst) throws Exception {
        if (!isMoveAllowed(src, dst)) {
            throw new Exception(String.format("Move not allowed %s -> %s (%s)", src,dst,this.currentTurn));
        }
        this.board._move(src, dst);
        this.currentTurn = this.currentTurn.getOppositeColor();
    }
    
    
    public boolean isMoveAllowed(Point src, Point dst) {
        Piece pieceToMove = this.board.getPieceAtLocation(src);
        if (pieceToMove==null){
            return false;
        }
        if (!Objects.equals(pieceToMove.getColor(), this.currentTurn)) {
            return false;
        }
        ArrayList<Point> allowed = this.board.getMoves(src);
        return allowed.contains(dst);
    }
    
    
    
    
}


