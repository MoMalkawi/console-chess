package chess.game.components.pieces.playable;

import chess.game.ChessGame;
import chess.game.components.board.components.Tile;

public interface Movable {

    public boolean canMove(ChessGame chessGame, Tile originTile, Tile targetTile);

}
