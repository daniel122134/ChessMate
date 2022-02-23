package chess.logic.game;

public enum PlayerColors {
    WHITE("white"),
    BLACK("black");
    
    private final String color;
    
    PlayerColors(String color) {
        this.color = color;
    }
    
    public String getColor() {
        return this.color;
    }
}
