package chess.game.validators;

import chess.game.ChessGame;
import chess.game.components.board.components.Tile;
import chess.game.components.pieces.Piece;

public class DangerValidator {

    private final ChessGame chessGame;

    public DangerValidator(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public boolean isPieceInDangerPostMovement(Tile tile, Tile movementOrigin, Tile movementTarget, boolean tileWhite) {
        Piece targetPieceTemp = chessGame.getBoard().getTile(movementTarget).getPiece();
        chessGame.getBoard().move(movementOrigin, movementTarget, true);
        boolean danger = isTileInDanger(tile, tileWhite);
        chessGame.getBoard().move(movementTarget, movementOrigin, true);
        chessGame.getBoard().placePiece(targetPieceTemp, movementTarget);
        return danger;
    }

    public boolean isTileInDanger(Tile tile, boolean white) {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                Tile currentTile = chessGame.getBoard().getTile(i, j);
                if(currentTile.equals(tile))
                    continue;

                if(currentTile.hasPiece()
                        && currentTile.getPiece().isWhite() != white
                        && currentTile.getPiece().canAttack(chessGame, currentTile, tile))
                    return true;
            }
        }
        return false;
    }

}
