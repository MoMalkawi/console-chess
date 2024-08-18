package chess.utils;

import chess.game.components.board.components.Tile;

public enum Direction {

    NORTH(0, 1),
    NORTH_EAST(1,1),
    EAST(1,0),
    SOUTH_EAST(1, -1),
    SOUTH(0, -1),
    SOUTH_WEST(-1, -1),
    WEST(-1, 0),
    NORTH_WEST(-1, 1);

    private final int xOffset;
    private final int yOffset;

    Direction(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public boolean isDiagonal() {
        return xOffset != 0 && yOffset != 0;
    }

    public Direction invert() {
        return getDirection(xOffset * -1, yOffset * -1);
    }

    public int getX() {
        return xOffset;
    }

    public int getY() {
        return yOffset;
    }

    public static Direction getDirection(int xOffset, int yOffset) {
        for(Direction direction : values()) {
            if(direction.xOffset == xOffset && direction.yOffset == yOffset)
                return direction;
        }
        return null;
    }

    public static Direction getUniformDirection(Tile origin, Tile target) {
        int xDiff = target.getX() - origin.getX();
        int yDiff = target.getY() - origin.getY();
        if(xDiff != 0 && yDiff != 0 && Math.abs(xDiff) != Math.abs(yDiff))
            return null;
        xDiff = Integer.compare(xDiff, 0);
        yDiff = Integer.compare(yDiff, 0);
        for(Direction direction : values()) {
            if(direction.xOffset == xDiff && direction.yOffset == yDiff)
                return direction;
        }
        return null;
    }

}
