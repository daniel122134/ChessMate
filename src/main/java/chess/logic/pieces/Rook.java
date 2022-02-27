package chess.logic.pieces;

import chess.logic.game.IConstraint;
import chess.logic.game.Move;
import chess.logic.game.PlayerColors;

import java.awt.Point;

public class Rook extends Piece {
    
    
    public Rook(PlayerColors color, Point location) {
        super(PieceType.ROOK, color, location);
    }
    
    public Rook(Rook rook) {
        this(rook.getColor(),rook.getLocation());
    }
    
    
    @Override
    public Move[] getMoves() {
        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        
        Move[] moves = new Move[1];
        IConstraint constraint = (n, p) -> {
            return p == null || !p.getColor().equals(this.getColor());
        };
        IConstraint lastConstraint = (n, p) -> {
            return p != null;
        };
        moves[0] = new Move(directions, constraint, lastConstraint);
        return moves;
        
    }
    
    @Override
    public String toString() {
        return "♜";
    }
    
    @Override
    public Piece copy() {
        return new Rook(this);
    }
}
