package chess.game.validators.movements.impl;

import chess.game.ChessGame;
import chess.game.components.board.components.Tile;
import chess.game.components.pieces.impl.Pawn;
import chess.game.validators.movements.MovementValidator;
import chess.utils.Direction;
import chess.game.data.moves.MoveResult;

public class EnPassantMovement extends MovementValidator {

    public EnPassantMovement(ChessGame chessGame, Tile originTile, Tile targetTile) {
        super(chessGame, originTile, targetTile);
    }

    private boolean isLastMoveNextToPawn(MoveResult lastMove) {
        return Math.abs(lastMove.getTarget().getX() - originTile.getX()) == 1;
    }

    @Override
    public boolean canMove() {
        return !targetTile.hasPiece() && verifyPosition() && canAttack();
    }

    private boolean verifyPosition() {
        Direction direction = Direction.getUniformDirection(originTile, targetTile);
        if(direction != null && direction.isDiagonal()) {
            if (!originTile.getPiece().isWhite())
                direction = direction.invert();
            return (direction.equals(Direction.NORTH_WEST) || direction.equals(Direction.NORTH_EAST)) &&
                    Math.abs(targetTile.getY() - originTile.getY()) == 1;
        }
        return false;
    }

    @Override
    public boolean canAttack() {
        return canEnPassantLastMove();
    }

    private boolean canEnPassantLastMove() {
        MoveResult lastMove = chessGame.getLastMove();
        if(lastMove != null &&
                lastMove.getTarget().getPiece() instanceof Pawn &&
                isLastMoveNextToPawn(lastMove)) {
            int yDiff = Math.abs(lastMove.getTarget().getY() - lastMove.getOrigin().getY());
            return yDiff == 2;
        }
        return false;
    }

    @Override
    public MoveResult formResult() {
        MoveResult result = new MoveResult(originTile, targetTile, true,
                chessGame.getLastMove().getTarget());
        result.setEnPassantConsumed(true);
        return result;
    }

}
