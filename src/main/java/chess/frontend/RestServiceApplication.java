package chess.frontend;

import chess.logic.game.Game;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestServiceApplication {
    
    public static Game game = new Game();
    
    public static void main(String[] args) {
        SpringApplication.run(RestServiceApplication.class, args);
    }
    
}
