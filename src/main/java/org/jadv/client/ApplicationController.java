package org.jadv.client;

import org.jadv.framework.Controller;
import org.jadv.model.level.Level;
import org.jadv.services.ImageService;
import org.jadv.services.LevelService;

/**
 * Controller of the Application Window.
 */
public class ApplicationController extends Controller {

    /**
     * Start level to load.
     */
    public static final String START_LEVEL = "testlevel";

    /**
     * Minimum Scale Factor (25%)
     */
    private static final int MIN_SCALE_FACTOR = 25;

    /**
     * Maximum Scale Factor (2500%)
     */
    private static final int MAX_SCALE_FACTOR = 2500;

    /**
     * Step size when changing scaling
     */
    private static final int SCALE_STEP = 10;

    /**
     * Step size when moving map.
     */
    private static final int STEP_SIZE = 10;

    /**
     * LevelService to load level.
     */
    private final LevelService levelService;

    /**
     * Creates a new instance of ApplicationController
     * @param imageService ImageService to use to load images.
     * @param levelService LevelService to use to load level.
     */
    public ApplicationController(final ImageService imageService, final LevelService levelService) {
        super(new ApplicationModel(), new ApplicationView());
        this.levelService = levelService;
        getModel().setImageStore(new ImageStore(imageService));
    }

    /**
     * Load data and show View.
     */
    public void show() {
        loadModel();
        view.show();
    }

    /**
     * Loads the data into the view.
     */
    private void loadModel() {
        final Level level = levelService.loadLevel(START_LEVEL);
        if (level == null) {
            throw new IllegalStateException("Unable to load Level " + START_LEVEL + "!");
        }

        // Set level
        getModel().setLevel(level);

        // Set scale factor
        getModel().setScaleFactor(100);

        // Set Corner
        getModel().setX(0);
        getModel().setY(0);

        getModel().getImageStore().checkAndLoadImages(level);
    }

    /**
     * Gets the ApplicationView instance used by this controller.
     * @return The ApplicationView.
     */
    @Override
    public ApplicationView getView() {
        return (ApplicationView) view;
    }

    /**
     * Gets the ApplicationModel used by this the controller.
     * @return ApplicationModel.
     */
    @Override
    public ApplicationModel getModel() {
        return (ApplicationModel) model;
    }

    /**
     * Handles an action comming from a view.
     * @param action Information about the action that was requested by the user in the form.
     */
    @Override
    public void handleAction(final Object action) {
        if (action instanceof ApplicationCommands command) {
            doCommand(command);
        }
    }

    /**
     * Moves the level.
     * @param dx Distance in x to move.
     * @param dy Distance in y to move.
     */
    @SuppressWarnings("PMD.ShortVariable")
    protected void move(final int dx, final int dy) {
        getModel().setX(Math.max(0, getModel().getX() + dx * getModel().getScaleFactor()/100));
        getModel().setY(Math.max(0, getModel().getY() + dy * getModel().getScaleFactor()/100));
        getModel().hasChanged();
    }

    /**
     * Scales the level.
     * @param scaleChange Change of the scaling.
     */
    protected void scale(final int scaleChange) {
        int newScale = getModel().getScaleFactor() + scaleChange;

        if (newScale < MIN_SCALE_FACTOR) {
            newScale = MIN_SCALE_FACTOR;
        }
        if (newScale > MAX_SCALE_FACTOR) {
            newScale = MAX_SCALE_FACTOR;
        }

        getModel().setScaleFactor(newScale);
        getModel().hasChanged();
    }

    /**
     * Handles a command.
     * @param command Command to handle.
     */
    private void doCommand(final ApplicationCommands command) {
        switch (command) {
            case MOVE_UP -> move(0, -STEP_SIZE);
            case MOVE_DOWN -> move(0, STEP_SIZE);
            case MOVE_LEFT -> move(-STEP_SIZE, 0);
            case MOVE_RIGHT -> move(STEP_SIZE, 0);
            case SCALE_OUT -> scale(SCALE_STEP);
            case SCALE_IN -> scale(-SCALE_STEP);
        }
    }

}
