package chess.game.data.moves;

import chess.game.components.board.components.Tile;

public class MoveQuery {

    private Tile origin;

    private Tile target;

    public MoveQuery(Tile origin, Tile target) {
        this.origin = origin;
        this.target = target;
    }

    public Tile getOrigin() {
        return origin;
    }

    public Tile getTarget() {
        return target;
    }

    public void setOrigin(Tile origin) {
        this.origin = origin;
    }

    public void setTarget(Tile target) {
        this.target = target;
    }

}
