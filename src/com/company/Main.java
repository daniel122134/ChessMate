package com.company;

import com.company.pieces.Bishop;
import com.company.pieces.Pawn;
import com.company.pieces.Piece;

import java.awt.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        
        Board board = new Board();
        ArrayList<Point> points= board.getMoves(new Point(1, 1));
        System.out.println(points);
    }
}
