package chess.game.validators.movements.impl;

import chess.game.ChessGame;
import chess.game.components.board.components.Tile;
import chess.utils.Direction;

public class PawnMovement extends GridMovement {

    public PawnMovement(ChessGame chessGame, Tile origin, Tile target) {
        super(chessGame, origin, target, 1);
    }

    @Override
    public boolean canMove() {
        Direction direction = Direction.getUniformDirection(originTile, targetTile);
        if(direction != null && !direction.isDiagonal()) {
            int moves = getMoveCount(direction);
            if(verifyMoveCount(direction, moves))
                return traceRoute(originTile, direction, moves);
        }
        return false;
    }

    @Override
    public boolean canAttack() {
        Direction direction = Direction.getUniformDirection(originTile, targetTile);

        if(targetTile.hasPiece() && direction != null && direction.isDiagonal())
            return verifyMoveCount(direction, getMoveCount(direction));

        return false;
    }

    private boolean verifyMoveCount(Direction direction, int moves) {
        boolean white = originTile.getPiece().isWhite();
        Direction temp = direction;
        if(!white)
            temp = temp.invert();
        if (temp.equals(Direction.NORTH))
            return moves > 0 && isAtStartingRank(white) ? (moves <= 2) : (moves == 1);
        else if(temp.equals(Direction.NORTH_EAST) || temp.equals(Direction.NORTH_WEST))
            return moves == 1;
        return false;
    }

    private boolean isAtStartingRank(boolean white) {
        return white ? originTile.getY() == 1 : originTile.getY() == 6;
    }


}
