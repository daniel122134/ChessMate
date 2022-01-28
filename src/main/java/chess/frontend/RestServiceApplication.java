package chess.frontend;

import chess.logic.Board;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestServiceApplication {
    public static Board board = new Board();
    
    public static void main(String[] args) {
        SpringApplication.run(RestServiceApplication.class, args);
    }

}
