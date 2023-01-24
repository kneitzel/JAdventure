package org.jadv.model.level;

import com.google.gson.annotations.JsonAdapter;
import lombok.*;
import org.jadv.model.Container;
import org.jadv.model.Position;
import org.jadv.model.SavedObject;
import org.jadv.model.objects.GameObject;
import org.jadv.serialization.ListOfSavedObjectAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * JAdventure Level
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Level extends SavedObject implements Container {

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
     * Game objects inside the level.
     */
    @JsonAdapter(ListOfSavedObjectAdapter.class)
    private List<GameObject> objects = new ArrayList<>();

    /**
     * Creates a new instance of Level.
     * @param name Name of the level.
     * @param width Width of the level.
     * @param height Height of the level.
     */
    public Level(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
    }

    public void removeObject(@NonNull GameObject obj) {
        objects.remove(obj);
    }

    public void addObject(@NonNull GameObject obj, int x, int y) {
        objects.add(obj);
        obj.setPosition(new Position(x, y, this));
    }

    @Override
    public List<GameObject> getChilds() {
        return objects;
    }
}
