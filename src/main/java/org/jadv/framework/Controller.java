package org.jadv.framework;

public abstract class Controller {
    /**
     * Model to use to hand over all required Data to the View.
     */
    protected Model model;

    /**
     * View used to display the data of the Model.
     */
    protected View view;

    /**
     * Initializes the controller. Should be called by the derived Class.
     * @param model Model to use.
     * @param view View to use.
     */
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        view.setModel(model);
        view.addController(this);
        view.init();
    }

    /**
     * Gets the view. Can be overriden with a return type that derived from View.
     * @return The view the controller is workinh on.
     */
    public abstract View getView();

    /**
     * Gets the model. Can be overriden with a return type that derived from Model.
     * @return The model the controller is working with.
     */
    public abstract Model getModel();

    /**
     * Handles an action. Is called by the View.
     * @param action Information about the action that was requested by the user in the form.
     */
    public abstract void handleAction(Object action);
}
