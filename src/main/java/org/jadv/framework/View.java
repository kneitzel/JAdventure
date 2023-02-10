package org.jadv.framework;

import java.util.ArrayList;
import java.util.List;

/**
 * A generic View for an MVC UI.
 */
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
    void setModel(Model model) {
        this.model = model;
        model.addListener( m -> updateView() );
    }

    /**
     * Adds the controller to the view.
     * @param controller Controller to add.
     */
    void addController(Controller controller) {
        this.controller.add(controller);
    }

    /**
     * Removes an controller from the view.
     * @param controller Controller to remove.
     */
    void removeController(Controller controller) {
        this.controller.remove(controller);
    }

    /**
     * Gets the model. Can be implemented including a cast to a child class of Model.
     * @return The Model.
     */
    public abstract Model getModel();

    /**
     * Initializes the view. Is called by the controller to set up the view.
     */
    public abstract void init();

    /**
     * Shows the view.
     */
    public abstract void show();

    /**
     * Updates the view so that the data of the model is shown.
     * This method is called whenever the model signals a change.
     */
    protected void updateView() {}

    /**
     * Updates the view if required. Copies data from controls to the model.
     */
    protected void updateModel() {}

    /**
     * Sends an action to the controller
     * @param action Action with additional information.
     */
    protected void sendAction(Object action) {
        updateModel();
        controller.forEach(c -> c.handleAction(action));
    }
}
