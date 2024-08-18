package chess.game.validators.movements.impl;

import chess.game.ChessGame;
import chess.game.components.board.components.Tile;
import chess.game.validators.movements.MovementValidator;

public class KnightMovement extends MovementValidator {

    public KnightMovement(ChessGame chessGame, Tile originTile, Tile targetTile) {
        super(chessGame, originTile, targetTile);
    }

    @Override
    public boolean canAttack() {
        return canMove();
    }

    @Override
    public boolean canMove() {
        return isLShapedMovement();
    }

    private boolean isLShapedMovement() {
        int xDiff = Math.abs(targetTile.getX() - originTile.getX());
        int yDiff = Math.abs(targetTile.getY() - originTile.getY());
        return (yDiff == 2 && xDiff == 1) || (yDiff == 1 && xDiff == 2);
    }

}
