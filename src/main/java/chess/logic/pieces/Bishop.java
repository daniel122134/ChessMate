package chess.logic.pieces;

import chess.logic.IConstraint;
import chess.logic.Move;

import java.awt.*;

public class Bishop extends Piece {


    public Bishop(String color, Point location) {
        super(Type.BISHOP, color, location);
    }


    @Override
    public Move[] getMoves() {
        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        Move[] moves = new Move[1];
        IConstraint constraint = (n, p) -> {
            return p == null || !p.GetColor().equals(this.GetColor());
        };
        IConstraint lastConstraint = (n, p) -> {
            return p != null;
        };
        moves[0] = new Move(directions, constraint, lastConstraint);
        return moves;

    }
    
    @Override
    public String toString() {
        return "B";
    }
}
