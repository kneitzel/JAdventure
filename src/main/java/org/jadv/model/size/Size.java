package org.jadv.model.size;

import com.google.gson.annotations.JsonAdapter;
import org.jadv.model.SavedObject;
import org.jadv.serialization.SavedObjectAdapter;

/**
 * Size of an GameObject
 */
@JsonAdapter(SavedObjectAdapter.class)
public abstract class Size extends SavedObject {
    public abstract int getWidth();
    public abstract int getHeight();
}
