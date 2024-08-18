package chess.utils;

public class Utils {

    private Utils() {}

    public static final String[] EMPTY_ARRAY = new String[0];

    public static boolean isNumber(String str) {
        return str != null && str.matches("^\\d+$");
    }

    public static String subStrArrayToStr(String[] array, int start, int end, char separator) {
        StringBuilder builder = new StringBuilder();
        for(int i = start; i < array.length; i++) {
            if(i == end)
                break;
            if(i > start)
                builder.append(separator);
            builder.append(array[i]);
        }
        return builder.toString();
    }

}
