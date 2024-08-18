package chess.game.data.moves;

import chess.game.components.board.components.Tile;
import chess.game.components.pieces.Piece;

public class MoveResult {

    public static final MoveResult EMPTY_RESULT = new MoveResult
            (null, null, false, null);

    private final boolean valid;

    private final Piece consumedPiece;

    private final Tile consumedPieceTile;

    private final Tile origin;

    private final Tile target;

    private boolean enPassantConsumed;

    private boolean castlingMove;

    public MoveResult(Tile origin, Tile target, boolean valid, Tile consumedPieceTile) {
        this.origin = origin;
        this.target = target;
        this.valid = valid;
        this.consumedPieceTile = consumedPieceTile;
        this.consumedPiece = consumedPieceTile != null ? consumedPieceTile.getPiece() : null;
    }

    public boolean isValid() {
        return valid;
    }

    public Tile getConsumedPieceTile() {
        return consumedPieceTile;
    }

    public Tile getOrigin() {
        return origin;
    }

    public Tile getTarget() {
        return target;
    }

    public boolean isEnPassantConsumed() {
        return enPassantConsumed;
    }

    public void setEnPassantConsumed(boolean enPassantConsumed) {
        this.enPassantConsumed = enPassantConsumed;
    }

    public Piece getConsumedPiece() {
        return consumedPiece;
    }

    public boolean isCastlingMove() {
        return castlingMove;
    }

    public void setCastlingMove(boolean castlingMove) {
        this.castlingMove = castlingMove;
    }

}
