package chess.logic.game;

import chess.logic.pieces.Piece;

public interface IConstraint {
    
    Boolean run(int stepNumber, Piece onSpot);
    
}

