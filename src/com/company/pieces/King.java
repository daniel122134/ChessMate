package com.company.pieces;

import com.company.IConstraint;
import com.company.Move;

import java.awt.*;
import java.util.ArrayList;

public class King extends Piece {
    
    public King(String color, Point location) {
        super(Type.KING, color, location);
    }
    
    
    @Override
    public Move[] getMoves() {
        ArrayList<Point> points = new ArrayList<Point>();
        Point location = this.getLocation();
        
        int[][] directions = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}, {1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        
        Move[] moves = new Move[1];
        IConstraint constraint = (n, p) -> {
            return p == null || !p.GetColor().equals(this.GetColor());
        };
        IConstraint lastConstraint = (n, p) -> {
            return true;
        };
        moves[0] = new Move(directions, constraint, lastConstraint);
        return moves;
        
    }
    @Override
    public String toString() {
        return "K";
    }
    
}
