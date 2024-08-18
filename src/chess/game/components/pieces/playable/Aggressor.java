package chess.game.components.pieces.playable;

import chess.game.ChessGame;
import chess.game.components.board.components.Tile;

public interface Aggressor {

    boolean canAttack(ChessGame chessGame, Tile originTile, Tile oppositionTile);

}
