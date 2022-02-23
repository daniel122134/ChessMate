package chess.frontend.rest;

import java.awt.Point;
import java.util.ArrayList;

public class MovesResponse {
    
    private final long id;
    private final ArrayList<Point> moves;
    
    public MovesResponse(long id, ArrayList<Point> moves) {
        this.id = id;
        this.moves = moves;
    }
    
    public long getId() {
        return id;
    }
    
    public ArrayList<Point> getContent() {
        return moves;
    }
}
