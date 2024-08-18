package chess.game.components.board.components;

import chess.game.components.pieces.Piece;
import chess.utils.Direction;

public class Tile {

    private final int x;
    private final int y;

    private final boolean dark;

    private Piece piece;

    public Tile(int x, int y, boolean dark) {
        this.x = x;
        this.y = y;
        this.dark = dark;
    }

    public Tile(int x, int y) {
        this(x, y, false);
    }

    public boolean isValid() {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    public Tile translate(int x, int y) {
        return new Tile(this.x + x, this.y + y);
    }

    public Tile translate(Direction direction) {
        return new Tile(this.x + direction.getX(), this.y + direction.getY());
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public boolean hasPiece() {
        return piece != null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isDark() {
        return dark;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Tile))
            return false;
        Tile tile = (Tile) o;
        return x == tile.x && y == tile.y;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Tile [X="+x+", Y="+y+", Piece="+(piece != null ? piece.getName() : "")+"]";
    }

}
