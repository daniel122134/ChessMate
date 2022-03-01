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
        Board tempBoard = new Board(board);
        ArrayList<Piece> pieces = tempBoard.getAllLivePiecesForColor(player);
        for (Piece p : pieces) {
            Point pointSrc = p.getLocation();
            ArrayList<Point> points = tempBoard.getMoves(pointSrc);
            for (Point dstPoint : points) {
                Point srcPoint = p.getLocation();
                Piece dstPiece = tempBoard.getPieceAtLocation(dstPoint);
                tempBoard._move(srcPoint, dstPoint);
//                Board afterMove = tempBoard.getBoardAfterMove(pointSrc, dstPoint);
                Double score = getBestScoreForBoard(tempBoard, player, player.getOppositeColor(), depthToGo -1);
                tempBoard._move(dstPoint, srcPoint);
                if (dstPiece!=null){
                    tempBoard.placePiece(dstPiece,dstPiece.getLocation());
                }
                if (move == null || score > move.getScore()) {
                    move = new MoveCandidate(pointSrc, dstPoint, score);
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
            for (Point dstPoint : points) {
//                Board afterMove = board.getBoardAfterMove(p.getLocation(), point);
                Point srcPoint = p.getLocation();
                Piece dstPiece = board.getPieceAtLocation(dstPoint);
                board._move(srcPoint, dstPoint);
                Double score = getBestScoreForBoard(board, player, currentTurn.getOppositeColor(), depthToGo - 1);
                board._move(dstPoint, srcPoint);
                if (dstPiece!=null){
                    board.placePiece(dstPiece,dstPiece.getLocation());
                }
                max = max != null ? Math.max(score, max) : score;
                min = min != null ? Math.min(score, min) : score;
                
            }
        }
        return player != currentTurn ? min : max;
    }
}
