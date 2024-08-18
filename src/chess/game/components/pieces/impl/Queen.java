package chess.game.components.pieces.impl;

import chess.game.ChessGame;
import chess.game.components.board.components.Tile;
import chess.game.components.pieces.Piece;
import chess.game.validators.movements.MovementValidator;
import chess.utils.Direction;
import chess.game.validators.movements.impl.GridMovement;

public class Queen extends Piece {

    public Queen(boolean white) {
        super("Queen", 9, -1, white);
    }

    @Override
    protected MovementValidator generateValidator(ChessGame chessGame, Tile origin, Tile target) {
        return new GridMovement(chessGame,  origin, target, -1,
                Direction.NORTH, Direction.EAST, Direction.WEST, Direction.SOUTH,
                Direction.NORTH_WEST, Direction.NORTH_EAST, Direction.SOUTH_WEST, Direction.SOUTH_EAST);
    }

    @Override
    public String getUnicodeSymbol() {
        return white ? "♕" : "♛";
    }

}
