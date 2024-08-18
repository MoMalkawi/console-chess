package chess.console.commands;

import chess.console.commands.impl.*;

import java.util.function.Supplier;

public enum CommandFactory {

    EXIT(Exit::new),
    MOVE(Move::new),
    RESET(Reset::new),
    SETNAME(SetName::new),
    STOPGAME(StopGame::new),
    START(Start::new),
    SHOW(Show::new),
    SETCOLOR(SetColor::new),
    POINTS(Points::new),
    TOGGLEBOARD(ToggleBoard::new);

    private final Supplier<Command> commandCreator;

    CommandFactory(Supplier<Command> commandCreator) {
        this.commandCreator = commandCreator;
    }

    public Command create() {
        return commandCreator.get();
    }

    public static CommandFactory getFactory(String keyword) {
        for(CommandFactory factory : values()) {
            if(factory.name().equalsIgnoreCase(keyword))
                return factory;
        }
        return null;
    }

}
