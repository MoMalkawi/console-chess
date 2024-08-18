package chess.console.commands.impl;

import chess.console.commands.Command;
import chess.console.io.Console;
import chess.console.io.messages.Message;
import chess.game.ChessGame;
import chess.game.data.GameConfig;
import chess.utils.AnsiColors;
import chess.utils.Utils;

public class SetColor extends Command {

    //(Dark Ordinal Start: 25, Bright Ordinal Start: 49)
    private final String[] colors = {
            "Black",
            "Red",
            "Green",
            "Yellow",
            "Blue",
            "Purple",
            "Cyan",
            "White"
    };
    public SetColor() {
        super("setcolor", 1);
    }

    @Override
    public void execute(ChessGame chessGame, String... args) {
        String color = Utils.subStrArrayToStr(args, 1, args.length, ' ');
        int colorIndex = getColorIndex(color);
        if(colorIndex != -1) {
            GameConfig.get().setDarkTileColor(AnsiColors.values()[25 + colorIndex]);
            GameConfig.get().setLightTileColor(AnsiColors.values()[49 + colorIndex]);
            Console.send("Color has been changed.", Message.Type.SUCCESS);
        } else Console.send("The color you entered is incorrect! try ?setcolor", Message.Type.ERROR);
    }

    private int getColorIndex(String color) {
        int index = 0;
        for(String c : colors) {
            if(color.equalsIgnoreCase(c))
                return index;
            index++;
        }
        return -1;
    }

    @Override
    public String helpText() {
        return "Command: " +
                AnsiColors.format(keyword, AnsiColors.BLACK_BACKGROUND_BRIGHT) +
                "\n\n" +
                "Description: " +
                AnsiColors.format("Sets the color of the chessboard." +
                                " Changes take effect instantly.",
                        AnsiColors.BLACK_BACKGROUND_BRIGHT) +
                "\n\n" +
                "Arguments: " +
                AnsiColors.format("Color", AnsiColors.PURPLE_BOLD) +
                "\n" +
                AnsiColors.format("Example: setcolor red",
                        AnsiColors.WHITE_UNDERLINED) +
                "\n\n" +
                "Available Colors: \n" +
                "Black, " +
                "Red, " +
                "Green, " +
                "Yellow, " +
                "Blue, " +
                "Purple, " +
                "Cyan, " +
                "White\n";
    }

}

