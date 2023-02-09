package org.jadv.framework;

/**
 * A generic View for an MVC UI.
 */
public abstract class View {

    /**
     * Model of the view
     */
    protected Model model;

    /**
     * Controller of this view.
     * The connection from View to Controller is normally done through observer pattern.
     * We have a controller base class so we can directly use that.
     */
    private Controller controller;

    /**
     * Sets the model.
     * @param model Model to use.
     */
    void setModel(Model model) {
        this.model = model;
        model.addListener( m -> updateView() );
    }

    /**
     * Sets the controller of the view.
     * @param controller Controller to use.
     */
    void setController(Controller controller) {
        this.controller = controller;
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
    public void updateView() {}

    /**
     * Updates the view if required. Copies data from controls to the model.
     */
    public void updateModel() {}

    public void doAction(Object action) {
        controller.doAction(action);
    }
}
