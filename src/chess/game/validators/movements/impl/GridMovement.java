package chess.game.validators.movements.impl;

import chess.game.ChessGame;
import chess.game.components.board.components.Tile;
import chess.game.validators.movements.MovementValidator;
import chess.utils.Direction;

import java.util.Arrays;

public class GridMovement extends MovementValidator {

    private final Direction[] directionsAllowed;

    private final int moveCount;

    public GridMovement(ChessGame chessGame, Tile origin, Tile target, int moveCount, Direction... directionsAllowed) {
        super(chessGame, origin, target);
        this.moveCount = moveCount;
        this.directionsAllowed = directionsAllowed;
    }

    @Override
    public boolean canMove() {
        Direction direction = Direction.getUniformDirection(originTile, targetTile);
        if(direction != null && isDirectionAllowed(direction)) {
            int moves = getMoveCount(direction);
            return (moveCount == -1 || moves <= moveCount) &&
                    traceRoute(originTile, direction, moves);
        }
        return false;
    }

    @Override
    public boolean canAttack() {
        return canMove();
    }

    private boolean isDirectionAllowed(Direction direction) {
        Direction temp = direction;
        if(!originTile.getPiece().isWhite())
            temp = temp.invert();
        return Arrays.asList(directionsAllowed).contains(temp);
    }

    protected int getMoveCount(Direction direction) {
        if(direction.isDiagonal())
            return Math.abs(targetTile.getY() - originTile.getY());
        int xDiff = targetTile.getX() - originTile.getX();
        int yDiff = targetTile.getY() - originTile.getY();
        return xDiff != 0 ? Math.abs(xDiff) : Math.abs(yDiff);
    }

}
