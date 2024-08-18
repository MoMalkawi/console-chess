package chess.console.commands.impl;

import chess.console.commands.Command;
import chess.console.io.Console;
import chess.console.io.messages.Message;
import chess.game.ChessGame;
import chess.game.data.GameConfig;
import chess.utils.AnsiColors;

public class ToggleBoard extends Command {

    public ToggleBoard() {
        super("toggleboard", -1);
    }

    @Override
    public void execute(ChessGame chessGame, String... args) {
        Console.send("Chessboard Display has been: " +
                (GameConfig.get().isDisplayBoard() ? "Disabled" : "Enabled"), Message.Type.SUCCESS);
        GameConfig.get().toggleDisplayBoard();
    }

    @Override
    public String helpText() {
        return "Command: " +
                AnsiColors.format(keyword, AnsiColors.BLACK_BACKGROUND_BRIGHT) +
                "\n\n" +
                "Description: " +
                AnsiColors.format("Toggles the chess board display.",
                        AnsiColors.BLACK_BACKGROUND_BRIGHT) +
                "\n";
    }

}
