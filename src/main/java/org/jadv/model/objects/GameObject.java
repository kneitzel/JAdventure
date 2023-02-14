package org.jadv.model.objects;

import com.google.gson.annotations.JsonAdapter;
import lombok.*;
import org.jadv.model.Position;
import org.jadv.model.SavedObject;
import org.jadv.model.size.Size;
import org.jadv.serialization.SavedObjectAdapter;

import java.io.Serial;

/**
 * An Object inside the JAdventure game.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper=true)
public class GameObject extends SavedObject {

    /**
     * Serial version UID of GameObject.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Name of the Object.
     */
    private String name;

    /**
     * Size of the Object.
     */
    @JsonAdapter(SavedObjectAdapter.class)
    private Size size;

    /**
     * Position of the Object.
     */
    private Position position;

    /**
     * Resource name of a graphic.
     */
    private String graphicResource;
}
