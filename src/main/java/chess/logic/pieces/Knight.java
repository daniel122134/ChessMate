package chess.logic.pieces;

import chess.logic.game.IConstraint;
import chess.logic.game.Move;
import chess.logic.game.PlayerColors;

import java.awt.Point;

public class Knight extends Piece {


    public Knight(PlayerColors color, Point location) {
        super(PieceType.KNIGHT, color, location);
    }

    public Knight(Knight knight) {
        this(knight.getColor(),knight.getLocation());
    }


    @Override
    public Move[] getMoves() {
        int[][] directions = {{1, 2}, {-1, 2}, {1, -2}, {-1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}};

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
        return "♞";
    }

    @Override
    public Piece copy() {
        return new Knight(this);
    }
}
