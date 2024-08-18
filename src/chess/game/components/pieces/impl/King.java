package chess.game.components.pieces.impl;

import chess.game.ChessGame;
import chess.game.components.board.components.Tile;
import chess.game.components.pieces.Piece;
import chess.game.validators.movements.MovementValidator;
import chess.game.validators.movements.impl.CastlingMovement;
import chess.game.validators.movements.impl.KingMovement;

public class King extends Piece {

    public King(boolean white) {
        super("King", 5, 1, white);
    }

    @Override
    protected MovementValidator generateValidator(ChessGame chessGame, Tile origin, Tile target) {
        KingMovement movement = new KingMovement(chessGame, origin, target);
        movement.setNextValidator(new CastlingMovement(chessGame, origin, target));
        return movement;
    }

    @Override
    public String getUnicodeSymbol() {
        return white ? "♔" : "♚";
    }

}
