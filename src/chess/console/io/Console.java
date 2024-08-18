package chess.console.io;

import chess.console.commands.CommandPoller;
import chess.console.io.messages.Message;
import chess.console.io.messages.MessageBuilder;
import chess.game.ChessGame;
import chess.utils.AnsiColors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {

    private static BufferedReader inputReader;

    private static boolean running = true;

    private Console() {}

    public static void init() {
        inputReader = new BufferedReader(new InputStreamReader(System.in));
        running = true;
    }

    public static void close() {
        if(inputReader != null) {
            try {
                inputReader.close();
            } catch (IOException e) {
                send("[System] Fatal closing error: \n" + e.getMessage(),
                        Message.Type.ERROR);
            }
        }
    }

    public static void send(Message message) {
        System.out.print(message.toString());
    }

    public static void send(String message, Message.Type type) {
        send(new MessageBuilder().
                text(message).effects(type.getColor()).build());
    }

    public static String receive(Message message) {
        send(message);
        try {
            return inputReader.readLine();
        } catch (IOException e) {
            send("[System] Fatal reading error: \n" + e.getMessage(),
                    Message.Type.ERROR);
        }
        return "";
    }

    public static String receive(String message) {
        return receive(new MessageBuilder().
                text(message).
                effects(AnsiColors.WHITE_BRIGHT).
                build());
    }

    public static void startPoller(ChessGame chessGame) {
        new CommandPoller(chessGame).listenForCommands();
    }

    public static boolean isRunning() {
        return running;
    }

    public static void setRunning(boolean running) {
        Console.running = running;
    }

    public static void printChessBoard(ChessGame game) {
        if(game.isRunning()) {
            Console.send(new MessageBuilder().start("\n").text(game.getPlayers()[1].toString()).build());
            Console.send(new MessageBuilder().text(game.getBoard().toString()).build());
            Console.send(new MessageBuilder().text(game.getPlayers()[0].toString()).end("\n\n").build());
        }
    }

}
