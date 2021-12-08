package com.company.pieces;

import com.company.Move;

import java.awt.*;
import java.util.ArrayList;

public class Knight extends Piece {

    public Knight(String color, Point location) {
        super(Type.KNIGHT, color, location);
    }

    @Override
    public Move[] freeMoves() {
        ArrayList<Point> points = new ArrayList<Point>();
        Point location = this.getLocation();

        int[][] directions = {{2, 1}, {1, 2}, {2, -1}, {1, -2}, {-2, 1}, {-1, 2}, {-2, -1}, {-1, -2}};

        for (int[] dir : directions) {
            Point temp = new Point(location.x + dir[0], location.y + dir[1]);
            if (!(temp.x > 7 || temp.x < 0 || temp.y > 7 || temp.y < 0)) {
                points.add(temp);
            }
        }

        return points;
    }

    @Override
    public ArrayList<Point> nonBlockedMoves() {
        return null;
    }

    @Override
    public ArrayList<Point> eatMoves() {
        return null;

    }

    @Override
    public Move emptyMoves() {
        return null;
    }
}
