package chess.console.commands.impl;

import chess.console.commands.Command;
import chess.console.io.Console;
import chess.console.io.messages.Message;
import chess.console.io.messages.MessageBuilder;
import chess.game.ChessGame;
import chess.game.components.board.components.Tile;
import chess.game.data.GameConfig;
import chess.game.data.moves.MoveQuery;
import chess.game.data.moves.MoveResult;
import chess.utils.AnsiColors;
import chess.utils.PositionUtils;

public class Move extends Command {

    public Move() {
        super("move", 2);
    }

    @Override
    public void execute(ChessGame chessGame, String... args) {
        if(!chessGame.isRunning()) {
            Console.send("There isn't a game running!", Message.Type.ERROR);
            return;
        }

        if(!PositionUtils.verifyPositionsFormat(args[1], args[2])) {
            Console.send("Positions format is incorrect! try ?move", Message.Type.ERROR);
            return;
        }

        MoveResult result = executeQuery(args[1], args[2], chessGame);
        if(result.isValid()) {
            Console.send("["+args[1]+"] -> ["+args[2]+"] moved.", Message.Type.SUCCESS);
            printConsumedPieces(result);
            printCastlingAlert(result);
            printBoard(chessGame);
        } else Console.send("Invalid Movement.", Message.Type.INFO);

    }

    private MoveResult executeQuery(String originRaw, String targetRaw, ChessGame chessGame) {
        return chessGame.executeMove(new MoveQuery(
                new Tile(PositionUtils.fileToIndex(originRaw.charAt(0)),
                        Integer.parseInt(String.valueOf(originRaw.charAt(1))) - 1),
                new Tile(PositionUtils.fileToIndex(targetRaw.charAt(0)),
                        Integer.parseInt(String.valueOf(targetRaw.charAt(1))) - 1)));
    }

    private void printBoard(ChessGame chessGame) {
        if(GameConfig.get().isDisplayBoard())
            Console.printChessBoard(chessGame);
    }

    private void printCastlingAlert(MoveResult result) {
        if(result.isCastlingMove())
            Console.send(new MessageBuilder()
                    .text("[Castling] !Castled! [Castling]")
                    .effects(AnsiColors.BLUE_BOLD_BRIGHT).build());
    }

    private void printConsumedPieces(MoveResult result) {
        if(result.getConsumedPiece() != null) {

            if(result.isEnPassantConsumed()) {
                Console.send(new MessageBuilder()
                        .text("[EnPassant] !Captured! [EnPassant]")
                        .effects(AnsiColors.BLUE_BOLD_BRIGHT).build());
            }

            Console.send("[" + result.getConsumedPiece().getUnicodeSymbol() + "] consumed!",
                    Message.Type.SUCCESS);
        }
    }

    @Override
    public String helpText() {
        return "Command: " +
                AnsiColors.format(keyword, AnsiColors.BLACK_BACKGROUND_BRIGHT) +
                "\n\n" +
                "Description: " +
                AnsiColors.format("Moves piece at position1 to position2, if found and if allowed.",
                        AnsiColors.BLACK_BACKGROUND_BRIGHT) +
                "\n\n" +
                "Arguments: " +
                AnsiColors.format("position1 ", AnsiColors.WHITE_BRIGHT) +
                AnsiColors.format("position2 ", AnsiColors.GREEN_BRIGHT) +
                "\n" +
                AnsiColors.format("Example: move d1 d4", AnsiColors.WHITE_UNDERLINED) +
                "\n";
    }

}
