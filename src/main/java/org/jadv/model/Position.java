package org.jadv.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

/**
 * Position stores the position of an GameObject inside the game and is immutable.
 */
@Getter
@AllArgsConstructor
public final class Position implements Serializable {

    /**
     * Serial version UID of Position
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * X Coordiante.
     */
    @SuppressWarnings("PMD.ShortVariable")
    private final double x;

    /**
     * Y Coordinate
     */
    @SuppressWarnings("PMD.ShortVariable")
    private final double y;

    /**
     * Parent of this Object.
     */
    private transient Container parent;

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
