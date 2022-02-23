package chess.frontend.rest;

import static chess.frontend.RestServiceApplication.board;

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
        
        ArrayList<Point> points = board.getMoves(new Point(x, y));
        
        return new MovesResponse(counter.incrementAndGet(), points);
    }
    
    @GetMapping("/random")
    public MovesResponse randomMove() {
        
		while (true) {
			int y = (int) (Math.random() * 8);
			int x = (int) (Math.random() * 8);
	
			ArrayList<Point> points = board.getMoves(new Point(x, y));
			if (points.size() > 0) {
                try {
                    board.move(new Point(x, y), points.get((int) (Math.random() * points.size())));
                    return new MovesResponse(counter.incrementAndGet(), points);
                } catch (Exception e) {
                    System.out.println(e + " " + x + "," + y);
                }
            }
        }
        
        
    }
}
