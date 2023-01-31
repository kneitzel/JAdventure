package org.jadv.model;

import org.jadv.model.objects.GameObject;

import java.util.List;

/**
 * A Container has a list of GameObject as children.
 */
public interface Container {
    /**
     * Gets the List of GameObject children.
     * @return List of GameObjects.
     */
    List<GameObject> getChildren();
}
