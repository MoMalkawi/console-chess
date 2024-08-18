package chess.game.components.pieces.impl;

import chess.game.ChessGame;
import chess.game.components.board.components.Tile;
import chess.game.components.pieces.Piece;
import chess.game.validators.movements.MovementValidator;
import chess.game.validators.movements.impl.KnightMovement;

public class Knight extends Piece {

    public Knight(boolean white) {
        super("Knight", 3, 3, white);
    }

    @Override
    protected MovementValidator generateValidator(ChessGame chessGame, Tile origin, Tile target) {
        return new KnightMovement(chessGame, origin, target);
    }

    @Override
    public String getUnicodeSymbol() {
        return white ? "♘" : "♞";
    }

}
