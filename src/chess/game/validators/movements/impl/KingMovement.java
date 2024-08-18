package chess.game.validators.movements.impl;

import chess.game.ChessGame;
import chess.game.components.board.components.Tile;
import chess.utils.Direction;

public class KingMovement extends GridMovement {

    public KingMovement(ChessGame chessGame, Tile origin, Tile target) {
        super(chessGame, origin, target, 1,
                Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST,
                Direction.NORTH_EAST, Direction.NORTH_WEST, Direction.SOUTH_EAST, Direction.SOUTH_WEST);
    }

    @Override
    public boolean canMove() {
        return super.canMove() &&
                chessGame.getCheckValidator().isFutureKingTileSafe(
                        originTile.getPiece().isWhite(), targetTile);
    }

}
