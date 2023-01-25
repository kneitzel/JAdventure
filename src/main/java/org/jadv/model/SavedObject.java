package org.jadv.model;

import com.google.gson.annotations.JsonAdapter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.jadv.serialization.SavedObjectAdapter;

/**
 * An instance that can be saved through an Adapter and contains the name of the class as field.
 */
@EqualsAndHashCode
@JsonAdapter(SavedObjectAdapter.class)
public abstract class SavedObject {
    /**
     * Class name of the instance.
     */
    @Getter
    private final String type = getClass().getName();
}
