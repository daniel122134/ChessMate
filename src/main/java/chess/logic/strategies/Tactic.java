package chess.logic.strategies;

import chess.logic.game.PlayerColors;

public interface Tactic {
    
    public MoveCandidate getMove(PlayerColors player, int level);
    
}
