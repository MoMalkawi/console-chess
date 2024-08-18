package chess.game.components.players.utils;

import chess.game.components.pieces.Piece;
import chess.game.components.players.Player;
import chess.utils.AnsiColors;

import java.util.Map;

public class PlayerPrinter {

    private final Player player;

    private final StringBuilder builder = new StringBuilder();

    public PlayerPrinter(Player player) {
        this.player = player;
    }

    public String print() {
        if(player.isWhite()) {
            appendAcquiredPieces(builder);
            appendNameBar(builder);
        } else {
            appendNameBar(builder);
            appendAcquiredPieces(builder);
        }
        return builder.toString();
    }

    private void appendNameBar(StringBuilder builder) {
        builder.append(AnsiColors.format(player.getName(),
                        player.isWhite() ? AnsiColors.WHITE_BOLD_BRIGHT : AnsiColors.BLACK_BOLD_BRIGHT))
                .append(" (").append(player.getPoints()).append(")");
    }

    private void appendAcquiredPieces(StringBuilder builder) {
        if(player.getAcquiredPieces().size() > 0) {
            if(!player.isWhite()) builder.append("\n");
            for (Map.Entry<Piece, Integer> pieceEntry : player.getAcquiredPieces().entrySet())
                builder.append(pieceEntry.getKey().getUnicodeSymbol())
                        .append('x').append(pieceEntry.getValue())
                        .append("  ");
            if(player.isWhite()) builder.append("\n");
        }
    }


}
