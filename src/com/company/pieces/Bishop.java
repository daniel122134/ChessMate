package com.company.pieces;

import com.company.IConstraint;
import com.company.Move;

import java.awt.*;
import java.util.ArrayList;

public class Bishop extends Piece {


    public Bishop(String color, Point location) {
        super(Type.BISHOP, color, location);
    }


    @Override
    public Move[] getMoves() {
        ArrayList<Point> points = new ArrayList<Point>();
        Point location = this.getLocation();

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
}
