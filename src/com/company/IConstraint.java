package com.company;

import com.company.pieces.Piece;

public interface IConstraint {

    Boolean run(int stepNumber, Piece onSpot);

}

