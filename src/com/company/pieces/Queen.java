package com.company.pieces;

import com.company.IConstraint;
import com.company.Move;

import java.awt.*;
import java.util.ArrayList;

public class Queen extends Piece {

    public Queen(String color, Point location) {
        super(Type.QUEEN, color, location);
    }


    @Override
    public Move[] getMoves() {
        ArrayList<Point> points = new ArrayList<Point>();
        Point location = this.getLocation();

        int[][] directions = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}, {1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        Move[] moves = new Move[1];
        IConstraint constraint = (n, p) -> {
            return !p.GetColor().equals(this.GetColor());
        };
        IConstraint lastConstraint = (n, p) -> {
            return p != null;
        };
        moves[0] = new Move(directions, constraint, lastConstraint);
        return moves;

    }


}
