package org.jadv.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Position stores the position of an GameObject inside the game.
 */
@Getter
@AllArgsConstructor
public final class Position {
    /**
     * X Coordiante.
     */
    private final int x;

    /**
     * Y Coordinate
     */
    private final int y;

    /**
     * Parent of this Object.
     */
    private transient final Container parent;

    /**
     * Created a new Position taking the x/y coordinates of the given position and adds the container.
     * @param position Position to take x / y coordinate from.
     * @param container New Container to set.
     * @return New Position with x/y coordinate of given position and parent Container.
     */
    public static Position createNewPosition(Position position, Container container) {
        return new Position(position.x, position.y, container);
    }
}
