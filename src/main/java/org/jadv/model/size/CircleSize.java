package org.jadv.model.size;

import lombok.*;

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
     * Radius of the circle around the object location.
     */
    private int radius;
}
