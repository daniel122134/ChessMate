package chess.logic.pieces;

public interface Copyable<C extends Copyable<C>> {
    C copy();
}
