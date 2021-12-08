package com.company;

import com.company.pieces.Bishop;
import com.company.pieces.Pawn;
import com.company.pieces.Piece;

import java.awt.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Piece x = new Bishop("white", new Point(1, 1));
        Piece y = new Pawn("black", new Point(1, 1));
        ArrayList<Point> points = new ArrayList<Point>();
        Move[] moves = x.getMoves();
        for (Move move : moves) {
            for (int[] dir : move.getDirs()) {
                int step = 1;

                while (true) {

                    Point temp = new Point(x.getLocation().x + (dir[0] * step), x.getLocation().y + (dir[1] * step));
                    if (temp.x > 7 || temp.x < 0 || temp.y < 0 || temp.y > 7) {
                        break;
                    }

                    if (!move.getIsAllowed().run(step, y)) {
                        break;
                    }

                    points.add(temp);
                    step += 1;


                    if (move.getIsLast().run(step, x)) {
                        break;
                    }

                }
            }
        }

        System.out.println(points);


        // write your code here

    }
}
