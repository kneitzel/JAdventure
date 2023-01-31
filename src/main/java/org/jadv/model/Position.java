package org.jadv.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Position stores the position of an GameObject inside the game.
 */
@Getter
@AllArgsConstructor
public class Position {
    /**
     * X Coordiante.
     */
    private int x;

    /**
     * Y Coordinate
     */
    private int y;

    /**
     * Parent of this Object.
     */
    private transient Container parent;

    public void setParent(final Container parent) {
        if (this.parent != null) throw new IllegalStateException("Parent already set!");

        this.parent = parent;
    }
}
