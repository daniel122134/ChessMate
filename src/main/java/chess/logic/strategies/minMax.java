package chess.logic.strategies;

import static chess.frontend.RestServiceApplication.game;

import chess.logic.game.Board;
import chess.logic.game.PlayerColors;
import chess.logic.pieces.Piece;

import java.awt.Point;
import java.util.ArrayList;

public class minMax implements Tactic {
    
    @Override
    public MoveCandidate getMove(PlayerColors player, int level) {
        return getBestMoveForBoard(game.board, player, level);
    }
    
    public MoveCandidate getBestMoveForBoard(Board board, PlayerColors player, int depthToGo) {
        MoveCandidate move = null;
        ArrayList<Piece> pieces = board.getAllLivePiecesForColor(player);
        for (Piece p : pieces) {
            Point pointSrc = p.getLocation();
            ArrayList<Point> points = board.getMoves(pointSrc);
            for (Point pointDst : points) {
                Board afterMove = board.getBoardAfterMove(pointSrc, pointDst);
                Double score = getBestScoreForBoard(afterMove, player, player.getOppositeColor(), depthToGo);
                if (move == null || score > move.getScore()) {
                    move = new MoveCandidate(pointSrc, pointDst, score);
                }
            }
        }
        return move;
    }
    
    public Double getBestScoreForBoard(Board board, PlayerColors player, PlayerColors currentTurn, int depthToGo) {
        if (depthToGo == 0) {
            return board.getRank(player);
        }
        Double max = null;
        Double min = null;
        ArrayList<Piece> pieces = board.getAllLivePiecesForColor(currentTurn);
        for (Piece p : pieces) {
            ArrayList<Point> points = board.getMoves(p.getLocation());
            for (Point point : points) {
                Board afterMove = board.getBoardAfterMove(p.getLocation(), point);
                Double score = getBestScoreForBoard(afterMove, player, currentTurn.getOppositeColor(), depthToGo - 1);
                max = max != null ? Math.max(score, max) : score;
                min = min != null ? Math.min(score, min) : score;
                
            }
        }
        return player != currentTurn ? min : max;
    }
}
