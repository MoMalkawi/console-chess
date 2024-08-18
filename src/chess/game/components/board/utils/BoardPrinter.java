package chess.game.components.board.utils;

import chess.game.components.board.ChessBoard;
import chess.game.components.board.components.Tile;
import chess.game.data.GameConfig;
import chess.utils.AnsiColors;

public class BoardPrinter {

    private final ChessBoard chessBoard;

    private final StringBuilder resultBuilder = new StringBuilder();

    private static final char EMPTY_SPACE = (char) (0x2003);

    public BoardPrinter(ChessBoard chessboard) {
        this.chessBoard = chessboard;
    }

    public String getBoardPrint() {
        printFileIndices();
        resultBuilder.append("\n");
        for(int i = 7; i >= 0; i--)
            printRankPieces(i);
        printFileIndices();
        return resultBuilder.toString();
    }

    private void printRankPieces(int y) {
        resultBuilder
                .append(AnsiColors.BLACK_BACKGROUND_BRIGHT.getCode())
                .append(' ').append(y + 1).append(' ')
                .append(AnsiColors.RESET.getCode());
        for(int x = 0; x < 8; x++) {
            Tile tile = chessBoard.getTile(x, y);
            resultBuilder
                    .append((tile.isDark() ? GameConfig.get().getLightTileColor() : GameConfig.get().getDarkTileColor()).getCode())
                    .append(' ')
                    .append(tile.hasPiece() ?
                            tile.getPiece().getUnicodeSymbol() : EMPTY_SPACE)
                    .append(' ')
                    .append(AnsiColors.RESET.getCode());
        }
        resultBuilder
                .append(AnsiColors.BLACK_BACKGROUND_BRIGHT.getCode())
                .append(' ').append(y + 1).append(' ')
                .append(AnsiColors.RESET.getCode());
        resultBuilder.append("\n");
    }

    private void printFileIndices() {
        resultBuilder
                .append(AnsiColors.BLACK_BACKGROUND_BRIGHT.getCode())
                .append(' ').append(EMPTY_SPACE).append(' ');
        for(int i = 0; i < 8; i++)
            resultBuilder
                    .append(' ')
                    .append((char) (97 + i))
                    .append(' ');
        resultBuilder.append(' ').append(EMPTY_SPACE).append(' ')
                .append(AnsiColors.RESET.getCode());
    }


}
