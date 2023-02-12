package org.jadv.client;

/**
 * Commands used in ApplicationView to signal user actions.
 */
public enum ApplicationCommands {
    /**
     * Moves level down.
     */
    MOVE_DOWN,

    /**
     * Moves level left.
     */
    MOVE_LEFT,

    /**
     * Moves level right.
     */
    MOVE_RIGHT,

    /**
     * Moves level up.
     */
    MOVE_UP,

    /**
     * Scaling level "inside".
     */
    SCALE_IN,

    /**
     * Scaling level "outside".
     */
    SCALE_OUT
}
