package chess.console.commands.impl;

import chess.console.commands.Command;
import chess.console.io.Console;
import chess.console.io.messages.Message;
import chess.game.ChessGame;
import chess.utils.AnsiColors;

public class Points extends Command {

    public Points() {
        super("points", -1);
    }

    @Override
    public void execute(ChessGame chessGame, String... args) {
        if(chessGame.isRunning())
            Console.send("Points: " + chessGame.getCurrentPlayer().getPoints(),
                    Message.Type.INFO);
        else
            Console.send("There isn't any game running!", Message.Type.ERROR);
    }

    @Override
    public String helpText() {
        return "Command: " +
                AnsiColors.format(keyword, AnsiColors.BLACK_BACKGROUND_BRIGHT) +
                "\n\n" +
                "Description: " +
                AnsiColors.format("Displays the player's points.",
                        AnsiColors.BLACK_BACKGROUND_BRIGHT) +
                "\n";
    }

}
