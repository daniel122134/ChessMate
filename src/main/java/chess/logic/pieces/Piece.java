package chess.logic.pieces;

import chess.logic.game.Move;
import chess.logic.game.PlayerColors;

import java.awt.Point;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public abstract class Piece implements Copyable<Piece> {
    
    private final PieceType type;
    private final PlayerColors color;
    private Point location;
    
    public Piece(PieceType type, PlayerColors color, Point location) {
        this.type = type;
        this.color = color;
        this.location = location;
    }
    
    
    public abstract Move[] getMoves();
    
    
    public abstract int getWorth();
    
    public PlayerColors getColor() {
        return this.color;
    }
    
    public Point getLocation() {
        return location;
    }
    
    public void setLocation(Point location) {
        this.location = location;
    }
    
    public HashMap<String, String> toJson() {
        HashMap<String, String> object = new HashMap<>();
        object.put("type", this.toString());
        object.put("color", this.color.getColor());
        return object;
    }
}
