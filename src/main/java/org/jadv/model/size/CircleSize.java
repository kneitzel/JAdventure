package org.jadv.model.size;

import lombok.*;

import java.io.Serial;

/**
 * Size of a circular object.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper=true)
public class CircleSize extends Size {

    /**
     * Serial version UID of CircleSize
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Radius of the circle around the object location.
     */
    private int radius;

    /**
     * Gets the width or the surrounding square.
     * @return Width of surrounding square.
     */
    @Override
    public int getWidth() {
        return 2*radius;
    }

    /**
     * Height of surrounding square.
     * @return Height of surrounding square.
     */
    @Override
    public int getHeight() {
        return 2*radius;
    }
}
