package org.jadv.model.size;

import lombok.*;

/**
 * Size of an circular object.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CircleSize {
    /**
     * Radius of the circle around the object location.
     */
    private int radius;
}
