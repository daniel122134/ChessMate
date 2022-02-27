package chess.logic.strategies;

import static chess.frontend.RestServiceApplication.game;

import chess.logic.game.Board;
import chess.logic.game.PlayerColors;
import chess.logic.pieces.Piece;
import javafx.util.Pair;

import java.awt.Point;
import java.util.ArrayList;

public class minMax implements Tactic{
    
    @Override
    public Pair<Point, Point> getMove(PlayerColors player) {
        ArrayList<Piece> pieces = game.board.getAllLivePiecesForColor(player);
        Double maxScore = null;
        Point src = null;
        Point dst = null;
        for (Piece p: pieces) {
            ArrayList<Point> points = game.getMoves(p.getLocation());
            for (Point point:points) {
                Board afterMove = game.board.getBoardAfterMove(p.getLocation(), point);
                Double score = afterMove.getRank(player);
                if ((maxScore == null || score > maxScore) && game.isMoveAllowed(p.getLocation(),point)){
                    maxScore = score;
                    src = p.getLocation();
                    dst = point;
                }
            }
        }
        return new Pair<>(src,dst);
    }
}
