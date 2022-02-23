package chess.logic.pieces;

import chess.logic.Move;

import java.awt.*;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

enum Type {
    KNIGHT,
    PAWN,
    QUEEN,
    KING,
    BISHOP,
    ROOK
}


public abstract class 
Piece {
    private final Type type;
    private final String color;
    Map<Type, Integer> map = Stream.of(
            new AbstractMap.SimpleImmutableEntry<>(Type.KNIGHT, 3),
            new AbstractMap.SimpleImmutableEntry<>(Type.PAWN, 1),
            new AbstractMap.SimpleImmutableEntry<>(Type.QUEEN, 7),
            new AbstractMap.SimpleImmutableEntry<>(Type.BISHOP, 5),
            new AbstractMap.SimpleImmutableEntry<>(Type.ROOK, 3))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    private Point location;

    public Piece(Type type, String color, Point location) {
        this.type = type;
        this.color = color;
        this.location = location;
    }


    public abstract Move[] getMoves();


    public int GetWorth() {
        return this.map.get(this.type);
    }

    public String GetColor() {
        return this.color;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
    
    public HashMap<String,String> toJson(){
        HashMap<String,String> object = new HashMap<>();
        object.put("type",this.toString());
        object.put("color", this.color);
        return object;
    }
}
