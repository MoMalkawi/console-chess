package chess.game.validators.movements.impl;

import chess.game.ChessGame;
import chess.game.components.board.components.Tile;
import chess.game.components.pieces.Piece;
import chess.game.validators.movements.MovementValidator;
import chess.utils.Direction;
import chess.game.data.moves.MoveResult;

public class CastlingMovement extends MovementValidator {

    public CastlingMovement(ChessGame chessGame, Tile originTile, Tile targetTile) {
        super(chessGame, originTile, targetTile);
    }

    @Override
    public boolean canAttack() {
        return false;
    }

    @Override
    public boolean canMove() {
        Direction direction = Direction.getUniformDirection(originTile, targetTile);
        boolean white = originTile.getPiece().isWhite();
        if(hasntMoved() && verifyDirection(direction) && verifyMoveCount())
            return verifyRook(direction, white) && verifyNeighboringTiles(direction, white);
        return false;
    }

    private boolean verifyRook(Direction direction, boolean white) {
        Direction invertedDirection = direction;
        if(!white)
            invertedDirection = direction.invert();
        Piece rook = chessGame.getBoard().getTile(
                invertedDirection.equals(Direction.EAST) ? 7 : 0, white ? 0 : 7).getPiece();
        return rook.getName().equalsIgnoreCase("rook") && rook.notMoved();
    }

    private boolean verifyNeighboringTiles(Direction direction, boolean white) {
        if(!chessGame.getCheckValidator().isKingChecked(white)) {
            Tile tile = originTile;
            for(int i = 1; i <= 2; i++) {
                tile = tile.translate(direction);
                if(chessGame.getBoard().getTile(tile).hasPiece() ||
                !chessGame.getCheckValidator().isFutureKingTileSafe(white, tile))
                    return false;
            }
            return true;
        }
        return false;
    }

    private boolean verifyDirection(Direction direction) {
        return direction != null &&
                (direction.equals(Direction.EAST) || direction.equals(Direction.WEST));
    }

    private boolean verifyMoveCount() {
        return Math.abs(targetTile.getX() - originTile.getX()) == 2;
    }

    private boolean hasntMoved() {
        return originTile.getPiece().notMoved();
    }

    @Override
    protected MoveResult formResult() {
        MoveResult result = new MoveResult(originTile, targetTile, true, targetTile);
        result.setCastlingMove(true);
        return result;
    }

}
