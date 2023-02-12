package org.jadv.model.size;

import com.google.gson.annotations.JsonAdapter;
import org.jadv.model.SavedObject;
import org.jadv.serialization.SavedObjectAdapter;

import java.io.Serial;

/**
 * Size of an GameObject
 */
@JsonAdapter(SavedObjectAdapter.class)
@SuppressWarnings("PMD.ShortClassName")
public abstract class Size extends SavedObject {
    /**
     * Serial version UID of RectangleSize
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Gets the width of the object (surronding box with object in middle)
     * @return Width of object.
     */
    public abstract int getWidth();

    /**
     * Gets the height of the object (surronding box with object in middle)
     * @return Height of object.
     */
    public abstract int getHeight();
}
