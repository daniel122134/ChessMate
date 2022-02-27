package chess.logic.strategies;

import static chess.frontend.RestServiceApplication.game;

import chess.logic.game.PlayerColors;
import javafx.util.Pair;

import java.awt.Point;
import java.util.ArrayList;

public class Random implements Tactic {
    
    public Pair<Point, Point> getMove(PlayerColors player) {
        while (true) {
            int y = (int) (Math.random() * 8);
            int x = (int) (Math.random() * 8);
            
            ArrayList<Point> points = game.getMoves(new Point(x, y));
            if (points.size() > 0) {
                
                Point src = new Point(x, y);
                Point dst = points.get((int) (Math.random() * points.size()));
                if (game.isMoveAllowed(src, dst)) {
                    return new Pair<>(src, dst);
                }
                
            }
        }
    }
}
