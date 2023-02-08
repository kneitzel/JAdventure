package org.jadv.client;

/**
 * A generic View for an MVC UI.
 */
public abstract class View {

    /**
     * Model of the view
     */
    protected final Model model;

    /**
     * Controller of this view
     */
    protected Controller controller;

    /**
     * Creates a new instance of the View.
     * @param model Model containing all data for the view.
     */
    public View (Model model) {
        this.model = model;
        model.addListener( m -> updateView() );
    }

    /**
     * Gets the model. Can be implemented including a cast to a child class of Model.
     * @return The Model.
     */
    public abstract Model getModel();

    /**
     * Gets the controller. Can be implemented including a cast to a child class of Controller.
     * @return The Controller.
     */
    public abstract Controller getController();

    /**
     * Gets the controller. Can be implemented including a cast to a child class of Controller.
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }

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
    public void updateView() {}

    /**
     * Updates the view if required. Copies data from controls to the model.
     */
    public void updateModel() {}
}
