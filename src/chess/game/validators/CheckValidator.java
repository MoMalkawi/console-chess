package chess.game.validators;

import chess.game.ChessGame;
import chess.game.components.board.components.Tile;
import chess.game.components.pieces.Piece;
import chess.game.components.pieces.impl.King;
import chess.utils.Direction;
import chess.game.data.moves.MoveResult;

public class CheckValidator {

    private final ChessGame chessGame;

    private Tile whiteKingTile;
    private Tile blackKingTile;

    public CheckValidator(ChessGame chessGame) {
        this.chessGame = chessGame;
        whiteKingTile = chessGame.getBoard().getTile(4, 0);
        blackKingTile = chessGame.getBoard().getTile(4, 7);
    }

    public boolean isCheckMate(boolean white) {
        Tile kingTile = getKingTile(white);
        return chessGame.getDangerValidator().isTileInDanger(kingTile, white) && !canKingEscapeFrom(kingTile, white);
    }

    private boolean canKingEscapeFrom(Tile kingTile, boolean white) {
        for (Direction direction : Direction.values()) {
            Tile translateTile = kingTile.translate(direction);
            if(translateTile.isValid()) {
                Tile tile = chessGame.getBoard().getTile(translateTile);
                if (!tile.hasPiece() && isFutureKingTileSafe(white, tile))
                    return true;
            }
        }
        return false;
    }

    public boolean isKingChecked(boolean white) {
        return chessGame.getDangerValidator().isTileInDanger(white ? whiteKingTile : blackKingTile, white);
    }

    public boolean isFutureKingTileSafe(boolean white, Tile tileToCheck) {
        Tile kingTile = white ? whiteKingTile : blackKingTile;
        return !chessGame.getDangerValidator().isPieceInDangerPostMovement(tileToCheck, kingTile, tileToCheck, white);
    }

    public boolean isFutureKingCheck(MoveResult moveResult) {
        boolean white = moveResult.getOrigin().getPiece().isWhite();
        if(moveResult.getOrigin().getPiece() instanceof King)
            return !isFutureKingTileSafe(white, moveResult.getTarget());

        return chessGame.getDangerValidator().isPieceInDangerPostMovement(getKingTile(white),
                moveResult.getOrigin(), moveResult.getTarget(), white);
    }

    public void updateKingTiles(Tile currentTile) {
        Piece pieceMoved = currentTile.getPiece();
        if(pieceMoved instanceof King) {
            if(pieceMoved.isWhite())
                whiteKingTile = currentTile;
            else
                blackKingTile = currentTile;
        }
    }

    private Tile getKingTile(boolean white) {
        return white ? whiteKingTile : blackKingTile;
    }

    public void reset() {
        whiteKingTile = chessGame.getBoard().getTile(4, 0);
        blackKingTile = chessGame.getBoard().getTile(4, 7);
    }

}
