package org.jadv.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

/**
 * Position stores the position of an GameObject inside the game.
 */
@Getter
@AllArgsConstructor
public class Position implements Serializable {

    /**
     * Serial version UID of Position
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * X Coordiante.
     */
    @SuppressWarnings("PMD.ShortVariable")
    private double x;

    /**
     * Y Coordinate
     */
    @SuppressWarnings("PMD.ShortVariable")
    private double y;

    /**
     * Parent of this Object.
     */
    private transient Container parent;

    /**
     * Sets the parent. Only works if parent is not set already.
     * @param parent Parent to set.
     */
    public void setParent(final Container parent) {
        if (this.parent != null) {
            throw new IllegalStateException("Parent already set!");
        }

        this.parent = parent;
    }
}
