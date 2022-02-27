package chess.frontend.rest;


import static chess.frontend.RestServiceApplication.game;

import chess.logic.game.PlayerColors;
import chess.logic.strategies.Random;
import chess.logic.strategies.minMax;
import javafx.util.Pair;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.Point;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MovesController {
    
    private final AtomicLong counter = new AtomicLong();
    
    @GetMapping("/moves")
    public MovesResponse greeting(
        @RequestParam(value = "x") int x, @RequestParam(value = "y") int y) {
        
        ArrayList<Point> points = game.getMoves(new Point(x, y));
        
        return new MovesResponse(counter.incrementAndGet(), points);
    }
    
    @GetMapping("/random")
    public MovesResponse randomMove() throws Exception {
        Pair<Point, Point> move = null;
        if (game.currentTurn == PlayerColors.BLACK) {
            move = new Random().getMove(PlayerColors.BLACK);
            
        } else {
            move = new minMax().getMove(PlayerColors.WHITE);
        }
        game.move(move.getKey(), move.getValue());
        return new MovesResponse(counter.incrementAndGet(), null);
        
    }
}
