package chess.game;

import chess.game.components.players.Player;
import chess.game.components.board.ChessBoard;
import chess.game.data.GameConfig;
import chess.game.executors.MovementsExecutor;
import chess.game.validators.CheckValidator;
import chess.game.validators.DangerValidator;
import chess.game.data.moves.MoveQuery;
import chess.game.data.moves.MoveResult;

public class ChessGame {

    private final Player[] players = new Player[2];

    private final ChessBoard board;

    private final CheckValidator checkValidator;
    private final DangerValidator dangerValidator;

    private int currentPlayerIndex = 0;

    private int movesCount = 0;

    public static final int MAX_MOVES = 50;

    private boolean running = false;

    private MoveResult lastMove;

    public ChessGame() {
        board = new ChessBoard();
        dangerValidator = new DangerValidator(this);
        checkValidator = new CheckValidator(this);
        createPlayers();
    }

    public void start() {
        if(!running) {
            reset();
            board.fillBoard();
            currentPlayerIndex = 0;
            running = true;
        }
    }

    public void reset() {
        running = false;
        board.resetTiles();
        createPlayers();
        checkValidator.reset();
        movesCount = 0;
        lastMove = null;
    }

    private void createPlayers() {
        players[0] = new Player(GameConfig.get().getFirstPlayerName(), true);
        players[1] = new Player(GameConfig.get().getSecondPlayerName(), false);
    }

    public MoveResult executeMove(MoveQuery query) {
        return new MovementsExecutor(this, query).execute();
    }

    public void stopGame() {
        running = false;
    }

    public Player getCurrentPlayer() {
        return players[currentPlayerIndex];
    }

    public Player[] getPlayers() {
        return players;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public boolean isWhiteTurn() {
        return currentPlayerIndex == 0;
    }

    public void switchTurn() {
        currentPlayerIndex = currentPlayerIndex == 0 ? 1 : 0;
    }

    public boolean isRunning() {
        return running;
    }

    public ChessBoard getBoard() {
        return board;
    }

    public MoveResult getLastMove() {
        return lastMove;
    }

    public CheckValidator getCheckValidator() {
        return checkValidator;
    }

    public DangerValidator getDangerValidator() {
        return dangerValidator;
    }

    public void setLastMove(MoveResult lastMove) {
        this.lastMove = lastMove;
    }

    public int getMovesCount() {
        return movesCount;
    }

    public void incrementMoves() {
        ++movesCount;
    }

}
