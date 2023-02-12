package org.jadv.model;

import com.google.gson.annotations.JsonAdapter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.jadv.serialization.SavedObjectAdapter;

import java.io.Serial;
import java.io.Serializable;

/**
 * An instance that can be saved through an Adapter and contains the name of the class as field.
 */
@EqualsAndHashCode
@JsonAdapter(SavedObjectAdapter.class)
@SuppressWarnings("PMD.AbstractClassWithoutAnyMethod")
public abstract class SavedObject implements Serializable {

    /**
     * Serial version UID of Level
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Class name of the instance.
     */
    @Getter
    private final String type = getClass().getName();
}
