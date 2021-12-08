package com.company.pieces;

import com.company.IConstraint;
import com.company.Move;

import java.awt.*;
import java.util.ArrayList;

public class Pawn extends Piece {
    private final Point startLocation;

    public Pawn(String color, Point location) {
        super(Type.PAWN, color, location);
        this.startLocation = location;
    }

    @Override
    public Move[] getMoves() {
        ArrayList<Point> points = new ArrayList<Point>();
        Point location = this.getLocation();
        Move[] moves = new Move[2];
        int dir = 1;
        if (startLocation.y != 1) {
            dir *= -1;
        }

        int[][] directions = {{0, dir}};


        IConstraint allowedConstraint = (n, p) -> {
            return p == null;
        };
        IConstraint lastConstraint = (n, p) -> {
            return true;
        };
        moves[0] = new Move(directions, allowedConstraint, lastConstraint);



        int[][]  eatDirections = {{1, dir}, {-1, dir}};


        allowedConstraint = (n, p) -> {
            return p != null && !p.GetColor().equals(this.GetColor());
        };
        lastConstraint = (n, p) -> {
            return true;
        };
        moves[1] = new Move(eatDirections, allowedConstraint, lastConstraint);


        return moves;

    }

}
