package chess.console.commands.impl;

import chess.console.commands.Command;
import chess.console.io.Console;
import chess.console.io.messages.Message;
import chess.game.ChessGame;
import chess.utils.AnsiColors;

public class Show extends Command {

    public Show() {
        super("show", -1);
    }

    @Override
    public void execute(ChessGame chessGame, String... args) {
        if(chessGame.isRunning()) {
            Console.printChessBoard(chessGame);
        } else
            Console.send("There is not any chess game to show!", Message.Type.ERROR);
    }

    @Override
    public String helpText() {
        return "Command: " +
                AnsiColors.format(keyword, AnsiColors.BLACK_BACKGROUND_BRIGHT) +
                "\n\n" +
                "Description: " +
                AnsiColors.format("Show the chess board.",
                        AnsiColors.BLACK_BACKGROUND_BRIGHT) +
                "\n";
    }

}
