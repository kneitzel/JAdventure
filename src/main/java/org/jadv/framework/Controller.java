package org.jadv.framework;

/**
 * This is a Java class Controller that provides a base implementation for a controller in the MVC
 * (Model-View-Controller) pattern. A controller in the MVC pattern is responsible for receiving input from the user
 * via the view, processing that input, and updating the model and view as necessary.
 * <p>
 * In this implementation, the Controller class provides functionality for setting the model and view, initializing
 * the controller, getting the view, getting the model, and handling an action.
 * <p>
 * The Controller class has two protected instance variables: model of type Model and view of type View. These variables
 * represent the model and view of the controller, respectively.
 * <p>
 * The Controller class provides a constructor that takes a Model object and a View object as arguments and sets the
 * model and view instance variables accordingly. The constructor also sets the model of the view using the setModel()
 * method of the View class, adds itself as a controller to the view using the addController() method of the View class,
 * and initializes the view using the init() method of the View class.
 * <p>
 * The Controller class provides several abstract methods that must be implemented by concrete controller classes:
 * <p>
 * The getView() method returns the view of the controller. This method can be overridden to return a more specific type
 * that derives from View.
 * The getModel() method returns the model of the controller. This method can be overridden to return a more specific
 * type that derives from Model.
 * The handleAction() method handles an action that was requested by the user. This method is called by the view and
 * takes an Object representing information about the action as an argument. This method must be implemented by deriving
 * classes.
 * Overall, the Controller class provides a basic structure for implementing a controller in the MVC pattern, which can
 * be extended and customized by deriving classes to meet specific application requirements.
 */
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
    public Controller(final Model model, final View view) {
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
