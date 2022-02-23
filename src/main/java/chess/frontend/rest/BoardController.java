package chess.frontend.rest;

import static chess.frontend.RestServiceApplication.board;

import chess.logic.Board;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.Point;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;


@RestController
public class BoardController {

	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/board")
	public BoardResponse greeting() {
		
		return new BoardResponse(counter.incrementAndGet(), board.matrix);
	}
}
