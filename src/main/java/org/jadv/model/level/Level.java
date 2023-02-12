package org.jadv.model.level;

import com.google.gson.annotations.JsonAdapter;
import lombok.*;
import org.jadv.model.Container;
import org.jadv.model.Position;
import org.jadv.model.SavedObject;
import org.jadv.model.objects.GameObject;
import org.jadv.serialization.ListOfSavedObjectAdapter;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

/**
 * JAdventure Level
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper=true)
public class Level extends SavedObject implements Container {

    /**
     * Serial version UID of Level
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Name of the Level.
     */
    private String name;

    /**
     * Width of the level.
     */
    private int width;

    /**
     * Height of the level.
     */
    private int height;

    /**
     * Resource name of a graphic.
     */
    private String graphicResource;

    /**
     * Game objects inside the level.
     */
    @JsonAdapter(ListOfSavedObjectAdapter.class)
    private final List<GameObject> objects = new ArrayList<>();

    /**
     * Removes an object from level.
     * @param obj Object to remove.
     */
    public void removeObject(@NonNull final GameObject obj) {
        objects.remove(obj);
    }

    /**
     * Adds an object to level at given localtion.
     * @param obj Object to add.
     * @param x X Coordinate of object.
     * @param y Y Coordinate of object
     */
    @SuppressWarnings("PMD.ShortVariable")
    public void addObject(@NonNull final GameObject obj, final int x, final int y) {
        objects.add(obj);
        obj.setPosition(new Position(x, y, this));
    }

    /**
     * Gets a list of all objects inside the level.
     * @return List of objects in level.
     */
    @Override
    public List<GameObject> getChildren() {
        return objects;
    }
}
