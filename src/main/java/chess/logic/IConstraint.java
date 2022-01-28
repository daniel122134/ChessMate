package chess.logic;

import chess.logic.pieces.Piece;

public interface IConstraint {

    Boolean run(int stepNumber, Piece onSpot);

}

