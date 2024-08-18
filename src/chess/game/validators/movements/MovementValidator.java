package chess.game.validators.movements;

import chess.game.ChessGame;
import chess.game.components.board.components.Tile;
import chess.utils.Direction;
import chess.game.data.moves.MoveResult;

public abstract class MovementValidator {

    protected MovementValidator nextValidator;

    protected Tile originTile;
    protected Tile targetTile;

    protected final ChessGame chessGame;

    protected MovementValidator(ChessGame chessGame, Tile originTile, Tile targetTile) {
        this.chessGame = chessGame;
        this.originTile = originTile;
        this.targetTile = targetTile;
    }

    public MoveResult simulateQuery() {
        boolean targetOccupied = targetTile.hasPiece();
        if((targetOccupied && canAttack()) || (!targetOccupied && canMove()))
            return formResult();

        return nextValidator != null ?
                nextValidator.simulateQuery() :
                MoveResult.EMPTY_RESULT;
    }

    public abstract boolean canAttack();

    public abstract boolean canMove();

    protected MoveResult formResult() {
        return new MoveResult(originTile, targetTile, true, targetTile);
    }

    protected boolean traceRoute(Tile origin, Direction direction, int moveCount) {
        int moves = 0;
        Tile current = origin;
        while(current.isValid()) {
            if(moves == (moveCount - 1))
                return true;
            current = current.translate(direction.getX(), direction.getY());
            Tile boardTile = chessGame.getBoard().getTile(current);
            if(boardTile.hasPiece())
                break;
            moves++;
        }
        return false;
    }

    public void setNextValidator(MovementValidator nextMovementValidator) {
        this.nextValidator = nextMovementValidator;
    }

}
