package chess.console.commands.impl;

import chess.console.commands.Command;
import chess.console.io.Console;
import chess.console.io.messages.Message;
import chess.game.ChessGame;
import chess.game.data.GameConfig;
import chess.utils.AnsiColors;
import chess.utils.Utils;

public class SetName extends Command {

    public SetName() {
        super("setname", 1);
    }

    @Override
    public void execute(ChessGame chessGame, String... args) {
        if(chessGame.isRunning()) {
            String newName = Utils.subStrArrayToStr(args, 1, args.length, ' ');
            setGameConfigName(chessGame.getCurrentPlayerIndex(), newName);
            Console.send("[" + chessGame.getCurrentPlayer().getName() +
                    "] -> [" + newName + "] changed.", Message.Type.SUCCESS);
            chessGame.getCurrentPlayer().setName(newName);
            Console.send("Changes will take effect momentarily.", Message.Type.INFO);
        } else Console.send("There isn't a chess.game running right now!", Message.Type.ERROR);
    }

    private void setGameConfigName(int playerIndex, String name) {
        if(playerIndex == 0)
            GameConfig.get().setFirstPlayerName(name);
        else
            GameConfig.get().setSecondPlayerName(name);
    }

    @Override
    public String helpText() {
        return "Command: " +
                AnsiColors.format(keyword, AnsiColors.BLACK_BACKGROUND_BRIGHT) +
                "\n\n" +
                "Description: " +
                AnsiColors.format("Sets the name of the current player." +
                                " Changes take effect next turn.",
                        AnsiColors.BLACK_BACKGROUND_BRIGHT) +
                "\n\n" +
                "Arguments: " +
                AnsiColors.format("Name", AnsiColors.PURPLE_BOLD) +
                "\n" +
                AnsiColors.format("Example: setname Mohammad Malkawi",
                        AnsiColors.WHITE_UNDERLINED) +
                "\n";
    }

}
