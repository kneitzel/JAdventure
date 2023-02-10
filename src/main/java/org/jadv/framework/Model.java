package org.jadv.framework;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * A simple model that implements the ObserverPattern to inform that it was changed.
 */
public abstract class Model {

    /**
     * List of consumers that are interested in changes of the model.
     * This is mainly used for view which needs to be updated whenever the model changes.
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
    public void addChangeListener(Consumer<Model> listener) {
        changeListenerList.add(listener);
    }

    /**
     * Removes a change listener of the list.
     * @param listener Listener to remove.
     */
    public void removeChangeListener(Consumer<Model> listener) {
        changeListenerList.remove(listener);
    }
}
