package chess.logic.game;


public class Move {
    
    IConstraint isAllowed;
    IConstraint isLast;
    private int[][] dirs;
    
    
    public Move(int[][] dirs, IConstraint isAllowed, IConstraint isLast) {
        this.dirs = dirs;
        this.isAllowed = isAllowed;
        this.isLast = isLast;
    }
    
    public int[][] getDirs() {
        return dirs;
    }
    
    public void setDirs(int[][] dirs) {
        this.dirs = dirs;
    }
    
    public IConstraint getIsAllowed() {
        return isAllowed;
    }
    
    public void setIsAllowed(IConstraint isAllowed) {
        this.isAllowed = isAllowed;
    }
    
    public IConstraint getIsLast() {
        return isLast;
    }
    
    public void setIsLast(IConstraint isLast) {
        this.isLast = isLast;
    }
}
