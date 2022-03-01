package chess.logic.pieces;

import chess.logic.game.IConstraint;
import chess.logic.game.Move;
import chess.logic.game.PlayerColors;

import java.awt.Point;

public class Pawn extends Piece {
    
    private Point startLocation;
    
    public Pawn(PlayerColors color, Point location) {
        super(PieceType.PAWN, color, location);
        this.startLocation = location;
    }
    
    public Pawn(Pawn pawn) {
        this(pawn.getColor(),pawn.getLocation());
        this.startLocation = pawn.startLocation;
    }
    
    @Override
    public Move[] getMoves() {
        Move[] moves = new Move[2];
        int dir = 1;
        if (startLocation.y == 6) {
            dir *= -1;
        }
        
        int[][] directions = {{0, dir}};
        
        IConstraint allowedConstraint = (n, p) -> {
            return p == null;
        };
        IConstraint lastConstraint = (n, p) -> {
            return (!this.getLocation().equals(this.startLocation) ||
                    (n != 1) || (p != null));
        };

        moves[0] = new Move(directions, allowedConstraint, lastConstraint);
        
        int[][] eatDirections = {{1, dir}, {-1, dir}};
        
        allowedConstraint = (n, p) -> {
            return p != null && !p.getColor().equals(this.getColor());
        };
        lastConstraint = (n, p) -> {
            return true;
        };
        moves[1] = new Move(eatDirections, allowedConstraint, lastConstraint);
        
        return moves;
        
    }
    
    @Override
    public String toString() {
        return "♟";
    }
    
    @Override
    public Piece copy() {
        return new Pawn(this);
    }
    
    
    @Override
    public int getWorth() {
        return 1;
    }
}
