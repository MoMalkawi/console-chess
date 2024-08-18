package chess.console.commands.impl;

import chess.console.commands.Command;
import chess.console.io.Console;
import chess.console.io.messages.MessageBuilder;
import chess.game.ChessGame;
import chess.utils.AnsiColors;

public class Exit extends Command {

    public Exit() {
        super("exit", -1);
    }

    @Override
    public void execute(ChessGame chessGame, String... args) {
        Console.setRunning(false);
        Console.send(new MessageBuilder().
                text("Thank you for playing, Good Bye!").
                effects(AnsiColors.BLUE_BOLD_BRIGHT).
                build());
    }

    @Override
    public String helpText() {
        return "Command: " +
                AnsiColors.format(keyword, AnsiColors.BLACK_BACKGROUND_BRIGHT) +
                "\n\n" +
                "Description: " +
                AnsiColors.format("Exits the program.",
                        AnsiColors.BLACK_BACKGROUND_BRIGHT) +
                "\n";
    }

}
