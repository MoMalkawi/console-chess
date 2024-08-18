package chess.game.components.pieces.impl;

import chess.game.ChessGame;
import chess.game.components.board.components.Tile;
import chess.game.components.pieces.Piece;
import chess.game.validators.movements.MovementValidator;
import chess.utils.Direction;
import chess.game.validators.movements.impl.GridMovement;

public class Rook extends Piece {

    public Rook(boolean white) {
        super("Rook", 5, -1, white);
    }

    @Override
    protected MovementValidator generateValidator(ChessGame chessGame, Tile origin, Tile target) {
        return new GridMovement(chessGame, origin, target, -1,
                Direction.NORTH, Direction.EAST, Direction.WEST, Direction.SOUTH);
    }

    @Override
    public String getUnicodeSymbol() {
        return white ? "♖" : "♜";
    }

}
