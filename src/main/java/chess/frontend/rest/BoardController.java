package chess.frontend.rest;

import static chess.frontend.RestServiceApplication.game;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;


@RestController
public class BoardController {
    
    private final AtomicLong counter = new AtomicLong();
    
    @GetMapping("/board")
    public BoardResponse greeting() {
        
        return new BoardResponse(counter.incrementAndGet(), game.board.matrix);
    }
}
