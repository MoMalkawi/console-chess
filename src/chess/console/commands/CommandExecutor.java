package chess.console.commands;

import chess.game.ChessGame;
import chess.utils.Utils;

public class CommandExecutor {

    private Command command;

    private final String input;

    private boolean helpRequest;

    private String[] arguments;

    public CommandExecutor(String input) {
        this.input = input;
    }

    public CommandExecutor findCommand() {
        CommandFactory commandFactory = CommandFactory.
                getFactory(extractKeyword());
        if(commandFactory != null) {
            Command tempCommand = commandFactory.create();
            if(helpRequest || verifyArguments(tempCommand))
                this.command = tempCommand;
        }
        return this;
    }

    private boolean verifyArguments(Command command) {
        arguments = extractArguments(command.getMinArgumentsCount());
        return (arguments.length - 1) >= command.getMinArgumentsCount();
    }

    public void executeIfFound(ChessGame chessGame) {
        if(command != null) {
            if(helpRequest)
                System.out.println(command.helpText());
            else
                command.execute(chessGame, arguments);
        }
    }

    private String extractKeyword() {
        String text = input;
        if(input.contains(" "))
            text = input.substring(0, input.indexOf(" "));
        if(text.startsWith("?") && text.length() > 1) {
            helpRequest = true;
            text = text.substring(1);
        }
        return text;
    }

    private String[] extractArguments(int minArgumentsCount) {
        if(minArgumentsCount <= 0)
            return Utils.EMPTY_ARRAY;
        return input.split(" ");
    }

}
