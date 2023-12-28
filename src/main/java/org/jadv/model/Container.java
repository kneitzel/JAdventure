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

    /**
     * Fixes the Parent Setting in all child elements.
     * <p>
     *     The parent setting is not serialized. So it is important, that after deserialization of a container all
     *     child entry positions ill be fixed.
     * </p>
     */
    default void fixParent() {
        getChildren().stream()
                .filter(child -> child.getPosition() != null)
                .forEach(child -> child.setPosition(Position.createNewPosition(child.getPosition(), this)));

    }
}
