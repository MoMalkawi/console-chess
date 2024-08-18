package chess.game.components.pieces;

import chess.console.io.Console;
import chess.console.io.messages.Message;
import chess.game.components.pieces.impl.*;
import chess.utils.Condition;

public enum PiecesFactory {

    ROOK {
        @Override
        public Piece create(boolean white) {
            return new Rook(white);
        }

    },
    KNIGHT {
        @Override
        public Piece create(boolean white) {
            return new Knight(white);
        }
    },
    BISHOP {
        @Override
        public Piece create(boolean white) {
            return new Bishop(white);
        }
    },
    QUEEN {
        @Override
        public Piece create(boolean white) {
            return new Queen(white);
        }
    },
    KING {
        @Override
        public Piece create(boolean white) {
            return new King(white);
        }
    },

    PAWN {
        @Override
        public Piece create(boolean white) {
            return new Pawn(white);
        }
    };

    public abstract Piece create(boolean white);

    public static PiecesFactory getFactory(String pieceName) {
        try {
            return valueOf(pieceName);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public static PiecesFactory getFactoryFromInput(Condition<String> condition) {
        while(true) {
            String input = Console.receive("Enter the name of the piece you want: ");
            if(input != null && !input.isEmpty()) {
                PiecesFactory factory = getFactory(input.toUpperCase());
                if(factory != null && condition.verify(input))
                    return factory;
            }
            Console.send("Something went wrong, try again.", Message.Type.ERROR);
        }
    }

}
