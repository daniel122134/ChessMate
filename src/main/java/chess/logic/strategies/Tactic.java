package chess.logic.strategies;

import chess.logic.game.PlayerColors;
import javafx.util.Pair;

import java.awt.Point;

public interface Tactic {
    
    public MoveCandidate getMove(PlayerColors player, int level);
    
}
