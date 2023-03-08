package org.jadv.framework;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * This is a Java class Model that provides a simple implementation of the Observer Pattern. The Observer Pattern is a
 * design pattern where an object, known as the subject, maintains a list of its dependents, called observers, and
 * notifies them automatically of any state changes, usually by calling one of their methods.
 * <p>
 * In this implementation, the Model class maintains a list of consumers that are interested in changes of the model.
 * This list is represented by an instance variable changeListenerList, which is a List of Consumer<Model> objects.
 * Consumer<Model> is a functional interface in Java 8 that takes an object of type Model as input and returns no output.
 * <p>
 * The Model class provides three methods for interacting with the change listener list:
 * <p>
 * The hasChanged() method notifies all listeners that the model has changed by calling the accept() method on each
 * Consumer<Model> object in the list, passing in this (i.e., the changed model) as an argument.
 * The addChangeListener() method adds a Consumer<Model> object to the list of change listeners.
 * The removeChangeListener() method removes a Consumer<Model> object from the list of change listeners.
 * This implementation is designed to be extended by concrete model classes, which would implement their own methods for
 * changing the state of the model and calling the hasChanged() method when appropriate. View classes that need to be
 * updated whenever the model changes can add themselves as listeners to the model by calling the addChangeListener()
 * method.
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
    public void addChangeListener(final Consumer<Model> listener) {
        changeListenerList.add(listener);
    }

    /**
     * Removes a change listener of the list.
     * @param listener Listener to remove.
     */
    public void removeChangeListener(final Consumer<Model> listener) {
        changeListenerList.remove(listener);
    }
}
