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
        view.setController(this);
        view.init();
    }

    public abstract View getView();

    public abstract Model getModel();

    public abstract void doAction(Object action);
}
