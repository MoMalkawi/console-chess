package chess.console.commands.impl;

import chess.console.commands.Command;
import chess.console.io.Console;
import chess.console.io.messages.Message;
import chess.game.ChessGame;
import chess.utils.AnsiColors;

public class StopGame extends Command {

    public StopGame() {
        super("stopgame", -1);
    }

    @Override
    public void execute(ChessGame chessGame, String... args) {
        if(chessGame.isRunning()) {
            chessGame.stopGame();
            Console.send("Game has been stopped.", Message.Type.SUCCESS);
        } else
            Console.send("There is no chess game running...", Message.Type.ERROR);
    }

    @Override
    public String helpText() {
        return "Command: " +
                AnsiColors.format(keyword, AnsiColors.BLACK_BACKGROUND_BRIGHT) +
                "\n\n" +
                "Description: " +
                AnsiColors.format("Stops the Game.",
                        AnsiColors.BLACK_BACKGROUND_BRIGHT) +
                "\n";
    }

}
