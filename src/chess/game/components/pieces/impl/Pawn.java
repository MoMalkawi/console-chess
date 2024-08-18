package chess.game.components.pieces.impl;

import chess.game.ChessGame;
import chess.game.components.board.components.Tile;
import chess.game.components.pieces.Piece;
import chess.game.validators.movements.MovementValidator;
import chess.game.validators.movements.impl.EnPassantMovement;
import chess.game.validators.movements.impl.PawnMovement;

public class Pawn extends Piece {

    public Pawn(boolean white) {
        super("Pawn", 1, 1, white);
    }

    @Override
    protected MovementValidator generateValidator(ChessGame chessGame, Tile origin, Tile target) {
        MovementValidator validator = new PawnMovement(chessGame, origin, target);
        validator.setNextValidator(new EnPassantMovement(chessGame, origin, target));
        return validator;
    }

    @Override
    public String getUnicodeSymbol() {
        return white ? "♙" : "♟︎";
    }

}
