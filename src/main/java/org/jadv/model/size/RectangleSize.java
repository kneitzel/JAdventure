package org.jadv.model.size;

import lombok.*;

/**
 * Rectangle Size
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper=true)
public class RectangleSize extends Size {

    /**
     * Width of the object.
     */
    int width;

    /**
     * Height of the object.
     */
    int height;
}
