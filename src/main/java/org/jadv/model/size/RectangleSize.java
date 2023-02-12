package org.jadv.model.size;

import lombok.*;

import java.io.Serial;

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
     * Serial version UID of RectangleSize
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Width of the object.
     */
    private int width;

    /**
     * Height of the object.
     */
    private int height;
}
