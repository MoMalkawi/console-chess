package chess.game.components.players;

import chess.game.components.pieces.Piece;
import chess.game.components.players.utils.PlayerPrinter;

import java.util.HashMap;
import java.util.Map;

public class Player {

    private String name;

    private int points;

    private final boolean white;

    private final Map<Piece, Integer> acquiredPieces = new HashMap<>();

    public Player(String name, boolean white) {
        this.name = name;
        this.white = white;
    }

    public boolean ownsPiece(Piece piece) {
        return piece != null && piece.isWhite() == white;
    }

    public void acquirePiece(Piece piece) {
        Integer amount = acquiredPieces.get(piece);
        acquiredPieces.put(piece, amount != null ? amount + 1 : 1);
        points += piece.getPoints();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public boolean isWhite() {
        return white;
    }

    public Map<Piece, Integer> getAcquiredPieces() {
        return acquiredPieces;
    }

    @Override
    public String toString() {
        return new PlayerPrinter(this).print();
    }



}
