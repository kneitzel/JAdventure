package org.jadv.framework;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a Java class View that provides a generic implementation for an MVC UI. The MVC (Model-View-Controller)
 * pattern is a design pattern that separates an application into three interconnected components: the model, the view,
 * and the controller.
 * <p>
 * In this implementation, the View class provides functionality for setting the model, adding and removing controllers,
 * initializing the view, showing the view, updating the view, and sending an action to the controller.
 * <p>
 * The View class has a protected instance variable model of type Model that represents the model of the view. This v
 * ariable can be set using the setModel() method, which takes a Model object as an argument and sets it as the model of
 * the view. The setModel() method also registers a change listener on the model using the addChangeListener() method of
 * the Model class, which calls the updateView() method whenever the model changes.
 * <p>
 * The View class has a private instance variable controller of type List<Controller> that represents the controller
 * interested in actions of this view. The View class provides methods for adding and removing controllers to this list
 * using the addController() and removeController() methods.
 * <p>
 * The View class provides several abstract methods that must be implemented by concrete view classes:
 * <p>
 * The getModel() method returns the model of the view. This method can be implemented to include a cast to a child
 * class of Model.
 * The show() method shows the view.
 * The updateView() method updates the view so that the data of the model is shown. This method is called whenever the
 * model signals a change.
 * The updateModel() method updates the model with data from the view's controls. This method can be implemented by
 * deriving classes.
 * The init() method initializes the view. This method is called by the controller to set up the view. This method can
 * be implemented by deriving classes.
 * The View class also provides a sendAction() method, which sends an action to the controller. This method updates the
 * model with data from the view's controls using the updateModel() method and then calls the handleAction() method of
 * each controller in the controller list, passing in the action as an argument.
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
     * Removes a controller from the view.
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
    @SuppressWarnings({"PMD.EmptyMethodInAbstractClassShouldBeAbstract", "EmptyMethod"})
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
    @SuppressWarnings({"PMD.EmptyMethodInAbstractClassShouldBeAbstract", "EmptyMethod"})
    public void updateView() {
        // Nothing to do, just an option for deriving classes.
    }

    /**
     * Updates the view if required. Copies data from controls to the model.
     */
    @SuppressWarnings({"PMD.EmptyMethodInAbstractClassShouldBeAbstract", "EmptyMethod"})
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
