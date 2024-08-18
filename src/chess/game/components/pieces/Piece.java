package chess.game.components.pieces;

import chess.game.ChessGame;
import chess.game.components.board.components.Tile;
import chess.game.components.pieces.playable.Aggressor;
import chess.game.components.pieces.playable.Movable;
import chess.game.validators.movements.MovementValidator;
import chess.game.data.moves.MoveQuery;
import chess.game.data.moves.MoveResult;

import java.util.Objects;

public abstract class Piece implements Movable, Aggressor {

    private final int points;

    protected final int maxMoveCount;

    protected final boolean white;

    private final String name;

    private boolean moved;

    protected Piece(String name, int points, int maxMoveCount, boolean white) {
        this.name = name;
        this.points = points;
        this.maxMoveCount = maxMoveCount;
        this.white = white;
    }

    public MoveResult simulateQuery(ChessGame chessGame, MoveQuery moveQuery) {
        return generateValidator(chessGame, moveQuery.getOrigin(), moveQuery.getTarget()).simulateQuery();
    }

    public boolean canMove(ChessGame chessGame, Tile origin, Tile target) {
        return generateValidator(chessGame, origin, target).canMove();
    }

    public boolean canAttack(ChessGame chessGame, Tile origin, Tile target) {
        return generateValidator(chessGame, origin, target).canAttack();
    }

    protected abstract MovementValidator generateValidator(ChessGame chessGame, Tile origin, Tile target);

    public abstract String getUnicodeSymbol();

    public int getPoints() {
        return points;
    }

    public boolean isWhite() {
        return white;
    }

    public String getName() {
        return name;
    }

    public boolean notMoved() {
        return !moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return white == piece.white && name.equals(piece.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(white, name);
    }

}
