package chess.frontend.rest;

import static chess.frontend.RestServiceApplication.board;

import java.awt.Point;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovesController {

	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/moves")
	public MovesResponse greeting(
		@RequestParam(value = "x") int x, @RequestParam(value = "y") int y) {
		
		ArrayList<Point> points = board.getMoves(new Point(x, y));
		
		return new MovesResponse(counter.incrementAndGet(), points);
	}
}
