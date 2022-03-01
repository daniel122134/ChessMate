package chess.frontend.rest;


import static chess.frontend.RestServiceApplication.game;

import chess.logic.game.PlayerColors;
import chess.logic.strategies.MoveCandidate;
import chess.logic.strategies.minMax;
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
        
        ArrayList<Point> points = game.board.getMoves(new Point(x, y));
        
        return new MovesResponse(counter.incrementAndGet(), points);
    }
    
    @GetMapping("/random")
    public MovesResponse randomMove() throws Exception {
        MoveCandidate move = null;
        if (game.currentTurn == PlayerColors.BLACK) {
            move = new minMax().getMove(PlayerColors.BLACK, 2);
            
        } else {
            move = new minMax().getMove(PlayerColors.WHITE, 2);  // level was 3
        }
        game.move(move.getSrc(), move.getDst());
        ArrayList<Point> points = new ArrayList<>();
        points.add(move.getSrc());
        points.add(move.getDst());
        return new MovesResponse(counter.incrementAndGet(), points);
        
    }
}
