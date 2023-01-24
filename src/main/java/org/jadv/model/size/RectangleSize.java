package org.jadv.model.size;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper=true)
public class RectangleSize extends Size {

    /**
     * Weidth of the object.
     */
    int weidth;

    /**
     * Height of the object.
     */
    int height;
}
