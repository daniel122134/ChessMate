package com.company.pieces;

import com.company.Move;

import java.awt.*;
import java.util.ArrayList;

public class Rook extends Piece {

    public Rook(String color, Point location) {
        super(Type.ROOK, color, location);
    }

    @Override
    public Move[] freeMoves() {
        return null;
    }

    @Override
    public ArrayList<Point> nonBlockedMoves() {
        ArrayList<Point> points = new ArrayList<Point>();
        Point location = this.getLocation();

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int[] dir : directions) {
            for (int i = 1; i < 8; i++) {

                Point temp = new Point(location.x + (dir[0] * i), location.y + (dir[1] * i));
                if (temp.x > 7 || temp.x < 0 || temp.y > 7 || temp.y < 0) {
                    break;
                }
                points.add(temp);
            }
        }

        return points;
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
