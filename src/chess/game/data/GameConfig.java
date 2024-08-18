package chess.game.data;

import chess.utils.AnsiColors;

import java.io.Serializable;

public class GameConfig implements Serializable {

    private static GameConfig instance = null;

    private String firstPlayerName = "White";
    private String secondPlayerName = "Black";

    private AnsiColors darkTileColor = AnsiColors.WHITE_BACKGROUND;
    private AnsiColors lightTileColor = AnsiColors.WHITE_BACKGROUND_BRIGHT;

    private boolean displayBoard = true;

    private GameConfig() {}

    public static GameConfig get() {
        if(instance == null)
            instance = new GameConfig();
        return instance;
    }

    public String getFirstPlayerName() {
        return firstPlayerName;
    }

    public void setFirstPlayerName(String firstPlayerName) {
        this.firstPlayerName = firstPlayerName;
    }

    public String getSecondPlayerName() {
        return secondPlayerName;
    }

    public void setSecondPlayerName(String secondPlayerName) {
        this.secondPlayerName = secondPlayerName;
    }

    public AnsiColors getDarkTileColor() {
        return darkTileColor;
    }

    public void setDarkTileColor(AnsiColors darkTileColor) {
        this.darkTileColor = darkTileColor;
    }

    public AnsiColors getLightTileColor() {
        return lightTileColor;
    }

    public void setLightTileColor(AnsiColors lightTileColor) {
        this.lightTileColor = lightTileColor;
    }

    public boolean isDisplayBoard() {
        return displayBoard;
    }

    public void toggleDisplayBoard() {
        displayBoard = !displayBoard;
    }

}
