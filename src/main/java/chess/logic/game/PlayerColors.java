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
    public PlayerColors getOppositeColor(){return this == PlayerColors.WHITE ? PlayerColors.BLACK : PlayerColors.WHITE;}
}
