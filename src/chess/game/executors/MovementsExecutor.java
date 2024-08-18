package chess.game.executors;

import chess.console.io.Console;
import chess.console.io.messages.Message;
import chess.console.io.messages.MessageBuilder;
import chess.game.ChessGame;
import chess.game.components.pieces.PiecesFactory;
import chess.game.components.pieces.impl.Pawn;
import chess.game.data.moves.MoveQuery;
import chess.game.data.moves.MoveResult;
import chess.utils.AnsiColors;

public class MovementsExecutor {

    private final ChessGame chessGame;

    private final MoveQuery moveQuery;

    private MoveResult moveResult;

    public MovementsExecutor(ChessGame chessGame, MoveQuery moveQuery) {
        this.chessGame = chessGame;
        this.moveQuery = moveQuery;
        moveQuery.setOrigin(chessGame.getBoard().getTile(moveQuery.getOrigin()));
        moveQuery.setTarget(chessGame.getBoard().getTile(moveQuery.getTarget()));
    }

    public MoveResult execute() {
        if(isQueryValid()) {
            moveResult = executeSimulator();
            if(moveResult.isValid() && validateKing()) {
                handlePawnTransformation();
                consumeAttackedPiece();
                moveOnBoard();
                handleCastling();
                handleEndOfTurn();
                return moveResult;
            }

        }
        return MoveResult.EMPTY_RESULT;
    }

    private boolean isQueryValid() {
        return isOriginValid() && isTargetValid();
    }

    private MoveResult executeSimulator() {
        return moveQuery.getOrigin().getPiece().simulateQuery(chessGame, moveQuery);
    }

    private boolean validateKing() {
        if(chessGame.getCheckValidator().isKingChecked(chessGame.isWhiteTurn())
                && chessGame.getCheckValidator().isFutureKingCheck(moveResult)) {
            Console.send("[DENIED] Your King is in CHECK! Protect Your King!",
                    Message.Type.ERROR);
            return false;
        }

        if(chessGame.getCheckValidator().isFutureKingCheck(moveResult)) {
            Console.send("[DENIED] [PINNED-PIECE] Your King will be in CHECK!",
                    Message.Type.ERROR);
            return false;
        }
        return true;
    }

    private void handlePawnTransformation() {
        if(shouldTransformPawn()) {
            PiecesFactory factory = PiecesFactory.getFactoryFromInput(s -> !s.equalsIgnoreCase("pawn"));
            chessGame.getBoard().placePiece(factory.create(chessGame.isWhiteTurn()), moveQuery.getOrigin());
        }
    }

    private void consumeAttackedPiece() {
        if(moveResult.getConsumedPiece() != null) {
            chessGame.getCurrentPlayer().acquirePiece(moveResult.getConsumedPiece());
            chessGame.getBoard().emptyTile(moveResult.getConsumedPieceTile());
        }
    }

    private void moveOnBoard() {
        chessGame.getBoard().move(moveQuery.getOrigin(), moveQuery.getTarget());
    }

    private void handleCastling() {
        if(moveResult.isCastlingMove()) {
            int file = moveResult.getTarget().getX() > moveResult.getOrigin().getX() ? 7 : 0;
            int rank = chessGame.isWhiteTurn() ? 0 : 7;
            chessGame.getBoard().move(chessGame.getBoard().getTile(file, rank),
                    moveResult.getTarget().translate(file > 0 ? -1 : 1,0));
        }
    }

    private void handleEndOfTurn() {
        chessGame.setLastMove(moveResult);
        chessGame.incrementMoves();
        chessGame.getCheckValidator().updateKingTiles(moveResult.getTarget());
        if(chessGame.getCheckValidator().isCheckMate(!chessGame.isWhiteTurn())) {
            handleCheckMate();
            return;
        }
        if(chessGame.getMovesCount() >= ChessGame.MAX_MOVES) {
            handleMaxMoves();
            return;
        }
        chessGame.switchTurn();
    }

    private void handleCheckMate() {
        Console.send(new MessageBuilder().text("[CHECKMATE] !"+
                        ((chessGame.getCurrentPlayerIndex() == 0) ? "WHITE" : "BLACK") +
                        " WON! [CHECKMATE]").
                effects(AnsiColors.CYAN_BOLD_BRIGHT).build());
        chessGame.reset();
    }

    private void handleMaxMoves() {
        Console.send(new MessageBuilder().text("[DRAW] !MAX MOVES REACHED! (50) [DRAW]").
                effects(AnsiColors.CYAN_BOLD_BRIGHT).build());
        chessGame.reset();
    }

    private boolean shouldTransformPawn() {
        return moveResult.getOrigin().getPiece() instanceof Pawn &&
                (moveResult.getTarget().getY() == 0 || moveResult.getTarget().getY() == 7);
    }

    private boolean isOriginValid() {
        if(!chessGame.getCurrentPlayer().
                ownsPiece(moveQuery.getOrigin().getPiece())) {
            Console.send("[QUERY-REJECTED] Origin move is either empty or friendly.",
                    Message.Type.ERROR);
            return false;
        }
        return true;
    }

    private boolean isTargetValid() {
        if(chessGame.getCurrentPlayer().ownsPiece(
                        moveQuery.getTarget().getPiece())) {
            Console.send("[QUERY-REJECTED] Target is a friendly!", Message.Type.ERROR);
            return false;
        }
        return true;
    }

}
