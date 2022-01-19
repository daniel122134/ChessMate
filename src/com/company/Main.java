package com.company;

import java.awt.Point;
import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args) throws Exception {
        
        Board board = new Board();
        board.move(new Point(1, 1), new Point(1, 2));
        System.out.println(board);
        
        while (true){
            int x = (int) (Math.random() * 8);
            int y = (int) (Math.random() * 8);
            ArrayList<Point> points = board.getMoves(new Point(x,y));
            if (points.size()>0){
                try{
                    board.move(new Point(x,y), points.get((int) Math.random() * points.size()));
                    System.out.println(board);
                }catch (Exception e){
                    System.out.println(e + " " + x + ","+y);
                }
                    
                Thread.sleep(1000);
    
            }
    
        }
    }
}
