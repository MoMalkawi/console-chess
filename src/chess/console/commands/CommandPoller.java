package chess.console.commands;

import chess.Chess;
import chess.console.io.Console;
import chess.console.io.messages.MessageBuilder;
import chess.game.ChessGame;
import chess.utils.AnsiColors;

public class CommandPoller {

    private final ChessGame chessGame;

    public CommandPoller(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public void listenForCommands() {
        while(Console.isRunning()) {
            new CommandExecutor(Console.receive(
                    new MessageBuilder().
                            text("[" + getCommandExecutor() + "]: ").
                            effects(AnsiColors.WHITE_BOLD_BRIGHT).
                            end("").
                            build()))
                    .findCommand()
                    .executeIfFound(chessGame);
        }
    }

    private String getCommandExecutor() {
        return chessGame.isRunning() ?
                chessGame.getCurrentPlayer().getName()
                :
                "System";
    }

}
