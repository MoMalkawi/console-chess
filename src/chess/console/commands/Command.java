package chess.console.commands;

import chess.game.ChessGame;

public abstract class Command {

    protected final String keyword;

    protected final int minArgumentsCount;

    protected Command(String keyword, int minArgumentsCount) {
        this.keyword = keyword;
        this.minArgumentsCount = minArgumentsCount;
    }

    public abstract void execute(ChessGame chessGame, String... args);

    public abstract String helpText();

    public int getMinArgumentsCount() {
        return minArgumentsCount;
    }

}
