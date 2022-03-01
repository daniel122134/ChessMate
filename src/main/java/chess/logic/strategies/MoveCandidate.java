package chess.logic.strategies;

import chess.logic.game.Board;

import java.awt.Point;

public class MoveCandidate {
    
    Point src;
    Point dst;
    Double score;
    
    public MoveCandidate(Point src, Point dst, Double score) {
        this.src = src;
        this.dst = dst;
        this.score = score;
    }
    public MoveCandidate(Point src, Point dst) {
        this.src = src;
        this.dst = dst;
        this.score = null;
    }
    
    public Double getScore(){
        return score;
    }
    
    public Point getDst() {
        return dst;
    }
    
    public Point getSrc() {
        return src;
    }
    
    public String toString(){
        return String.format("%s,%s", src,dst);
    }
}
