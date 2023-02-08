package org.jadv.client;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * A simple model that implements the ObserverPattern to inform that it was changed.
 */
public abstract class Model {

    /**
     * List of people that are interested in any change events.
     */
    private final List<Consumer<Model>> changeListenerList = new ArrayList<>();

    /**
     * Informs all Listeners that the model has changed. Parameter is the changed model.
     */
    public void hasChanged() {
        changeListenerList.forEach(l -> l.accept(this));
    }

    /**
     * Adds a Listener to the change listener list.
     * @param listener Listener to add.
     */
    public void addListener(Consumer<Model> listener) {
        changeListenerList.add(listener);
    }

    /**
     * Removes a change listener of the list.
     * @param listener Listener to remove.
     */
    public void removeListener(Consumer<Model> listener) {
        changeListenerList.remove(listener);
    }
}
