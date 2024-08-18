package chess.game.components.board;

import chess.game.components.board.components.Tile;
import chess.game.components.pieces.Piece;
import chess.game.components.board.utils.BoardPrinter;
import chess.game.components.pieces.PiecesFactory;
import chess.utils.PositionUtils;

public class ChessBoard {

    private final Tile[][] tiles = new Tile[8][8];

    public ChessBoard() {
        initializeTiles();
    }

    public void placePiece(Piece piece, int x, int y) {
        tiles[x][y].setPiece(piece);
    }

    public void placePiece(Piece piece, Tile tile) {
        placePiece(piece, tile.getX(), tile.getY());
    }

    public void emptyTile(int x, int y) {
        placePiece(null, x, y);
    }

    public void emptyTile(Tile tile) {
        emptyTile(tile.getX(), tile.getY());
    }

    private void initializeTiles() {
        boolean darkTile = false;
        for(int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                tiles[x][y] = new Tile(x, y, darkTile);
                darkTile = !darkTile;
            }
            darkTile = !darkTile;
        }
    }

    public void resetTiles() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++)
                emptyTile(i, j);
        }
    }

    public void move(Tile origin, Tile target, boolean simulation) {
        Piece pieceToMove = getTile(origin.getX(), origin.getY()).getPiece();
        if(!simulation)
            pieceToMove.setMoved(true);
        placePiece(pieceToMove, target.getX(), target.getY());
        emptyTile(origin.getX(), origin.getY());
    }

    public void move(Tile origin, Tile target) {
        move(origin, target, false);
    }

    public void fillBoard() {
        fillPawns();
        fillExtremes();
    }

    private void fillExtremes() {
        for(int i = 0; i < 5; i++) {
            PiecesFactory factory;
            if(i < 3) {
                factory = PiecesFactory.values()[i];
                placePiece(factory.create(true), 7 - i, 0);
                placePiece(factory.create(false), 7 - i, 7);
            } else if(i == 3)
                factory = PiecesFactory.QUEEN;
            else
                factory = PiecesFactory.KING;
            placePiece(factory.create(true), i, 0);
            placePiece(factory.create(false), i, 7);
        }
    }

    private void fillPawns() {
        for(int i = 0; i < 8; i++) {
            placePiece(PiecesFactory.PAWN.create(true), i, 1);
            placePiece(PiecesFactory.PAWN.create(false), i, 6);
        }
    }

    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }

    public Tile getTile(Tile tile) { return tiles[tile.getX()][tile.getY()]; }

    @SuppressWarnings("unused")
    public Tile getTile(char file, int rank) {
        return getTile(PositionUtils.fileToIndex(file), rank - 1);
    }

    @Override
    public String toString() {
        return new BoardPrinter(this).getBoardPrint();
    }

}
