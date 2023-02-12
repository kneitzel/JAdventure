package org.jadv.framework;

import java.util.ArrayList;
import java.util.List;

/**
 * A generic View for an MVC UI.
 */
@SuppressWarnings("PMD.ShortClassName")
public abstract class View {

    /**
     * Model of the view
     */
    protected Model model;

    /**
     * Controller Interested in actions of this view.
     */
    private final List<Controller> controller = new ArrayList<>();

    /**
     * Sets the model.
     * @param model Model to use.
     */
    /* default */ void setModel(final Model model) {
        this.model = model;
        model.addChangeListener(m -> updateView() );
    }

    /**
     * Adds the controller to the view.
     * @param controller Controller to add.
     */
    public void addController(final Controller controller) {
        this.controller.add(controller);
    }

    /**
     * Removes an controller from the view.
     * @param controller Controller to remove.
     */
    public void removeController(final Controller controller) {
        this.controller.remove(controller);
    }

    /**
     * Gets the model. Can be implemented including a cast to a child class of Model.
     * @return The Model.
     */
    public abstract Model getModel();

    /**
     * Initializes the view. This is called by the controller to set up the view.
     */
    @SuppressWarnings("PMD.EmptyMethodInAbstractClassShouldBeAbstract")
    public void init() {
        // Nothing to do, just an option for deriving classes.
    }

    /**
     * Shows the view.
     */
    public abstract void show();

    /**
     * Updates the view so that the data of the model is shown.
     * This method is called whenever the model signals a change.
     */
    @SuppressWarnings("PMD.EmptyMethodInAbstractClassShouldBeAbstract")
    protected void updateView() {
        // Nothing to do, just an option for deriving classes.
    }

    /**
     * Updates the view if required. Copies data from controls to the model.
     */
    @SuppressWarnings("PMD.EmptyMethodInAbstractClassShouldBeAbstract")
    protected void updateModel() {
        // Nothing to do, just an option for deriving classes.
    }

    /**
     * Sends an action to the controller
     * @param action Action with additional information.
     */
    protected void sendAction(final Object action) {
        updateModel();
        controller.forEach(c -> c.handleAction(action));
    }
}
