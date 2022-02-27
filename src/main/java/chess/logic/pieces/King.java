package chess.logic.pieces;

import chess.logic.game.IConstraint;
import chess.logic.game.Move;
import chess.logic.game.PlayerColors;

import java.awt.Point;
import java.util.ArrayList;

public class King extends Piece {
    
    public King(PlayerColors color, Point location) {
        super(PieceType.KING, color, location);
    }
    
    public King(King king) {
        this(king.getColor(),king.getLocation());
    }
    
    
    @Override
    public Move[] getMoves() {
        ArrayList<Point> points = new ArrayList<Point>();
        Point location = this.getLocation();
        
        int[][] directions = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}, {1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        
        Move[] moves = new Move[1];
        IConstraint constraint = (n, p) -> {
            return p == null || !p.getColor().equals(this.getColor());
        };
        IConstraint lastConstraint = (n, p) -> {
            return true;
        };
        moves[0] = new Move(directions, constraint, lastConstraint);
        return moves;
        
    }
    
    @Override
    public String toString() {
        return "♚";
    }
    
    @Override
    public Piece copy() {
        return new King(this);
    }
}
