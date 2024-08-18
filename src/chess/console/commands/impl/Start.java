package chess.console.commands.impl;

import chess.console.commands.Command;
import chess.console.io.Console;
import chess.console.io.messages.Message;
import chess.game.ChessGame;
import chess.utils.AnsiColors;

public class Start extends Command {

    public Start() {
        super("start", -1);
    }

    @Override
    public void execute(ChessGame chessGame, String... args) {
        if(!chessGame.isRunning()) {
            chessGame.start();
            Console.send("Game has been started.", Message.Type.SUCCESS);
            Console.printChessBoard(chessGame);
        } else
            Console.send("There is already a chess game running...", Message.Type.ERROR);
    }

    @Override
    public String helpText() {
        return "Command: " +
                AnsiColors.format(keyword, AnsiColors.BLACK_BACKGROUND_BRIGHT) +
                "\n\n" +
                "Description: " +
                AnsiColors.format("Start a Chess Game.",
                        AnsiColors.BLACK_BACKGROUND_BRIGHT) +
                "\n";
    }

}
