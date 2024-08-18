package chess;

import chess.game.ChessGame;
import chess.console.io.Console;

public class Chess {

    public static void main(String[] args)  {
        Console.init();
        ChessGame chessGame = new ChessGame();
        Console.startPoller(chessGame);
        Console.close();
    }


}
