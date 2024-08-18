package chess.utils;

import java.util.regex.Pattern;

public class PositionUtils {

    private PositionUtils() {}

    public static char indexToFile(int index) {
        return (char) (65 + index);
    }

    public static int fileToIndex(char file) {
        return Character.toUpperCase(file) - 65;
    }

    public static boolean verifyPositionFormat(String position) {
        return Pattern.matches("^[A-Ha-h][1-8]$", position);
    }

    public static boolean verifyPositionsFormat(String... positions) {
        for(String position : positions) {
            if(!verifyPositionFormat(position)) {
                System.out.println(AnsiColors.format("[" + position +
                                "] format is wrong.",
                        AnsiColors.RED_BOLD));
                return false;
            }
        }
        return true;
    }

}
