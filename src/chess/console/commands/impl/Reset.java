package chess.console.commands.impl;

import chess.console.commands.Command;
import chess.console.io.Console;
import chess.console.io.messages.Message;
import chess.game.ChessGame;
import chess.utils.AnsiColors;

public class Reset extends Command {

    public Reset() {
        super("reset", -1);
    }

    @Override
    public void execute(ChessGame chessGame, String... args) {
        chessGame.reset();
        Console.send("Game has been reset.", Message.Type.SUCCESS);
    }

    @Override
    public String helpText() {
        return "Command: " +
                AnsiColors.format(keyword, AnsiColors.BLACK_BACKGROUND_BRIGHT) +
                "\n\n" +
                "Description: " +
                AnsiColors.format("Resets the Chess Game.",
                        AnsiColors.BLACK_BACKGROUND_BRIGHT) +
                "\n";
    }

}
