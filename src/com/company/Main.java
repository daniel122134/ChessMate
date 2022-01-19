package com.company;

import java.awt.Point;
import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args) throws Exception {
        
        Board board = new Board();
        ArrayList<Point> points = board.getMoves(new Point(1, 1));
        System.out.println(points);
        board.move(new Point(1, 1), new Point(1, 2));
        System.out.println(board);
    }
}
