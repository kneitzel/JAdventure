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
     * ImageService to load images.
     */
    private final ImageService imageService;

    /**
     * LevelService to load level.
     */
    private final LevelService levelService;

    /**
     * Creates a new instance of ApplicationController
     * @param imageService ImageService to use to load images.
     * @param levelService LevelService to use to load level.
     */
    public ApplicationController(ImageService imageService, LevelService levelService) {
        super(new ApplicationModel(), new ApplicationView());
        this.imageService = imageService;
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
        Level level = levelService.loadLevel(START_LEVEL);
        if (level == null) throw new IllegalStateException("Unable to load Level " + START_LEVEL + "!");

        // Set level
        getModel().setLevel(level);

        // Set scale factor
        getModel().setScaleFactor(100);

        // Set Corner
        getModel().setX(0);
        getModel().setY(0);

        getModel().getImageStore().checkAndLoadImages(level);
    }

    @Override
    public ApplicationView getView() {
        return (ApplicationView) view;
    }

    @Override
    public ApplicationModel getModel() {
        return (ApplicationModel) model;
    }

    @Override
    public void doAction(Object action) {
        if (action instanceof ApplicationCommands command) {
            doCommand(command);
        }
    }

    protected void move(int dx, int dy) {
        getModel().setX(Math.max(0, getModel().getX() + dx * getModel().getScaleFactor()/100));
        getModel().setY(Math.max(0, getModel().getY() + dy * getModel().getScaleFactor()/100));
        getModel().hasChanged();
    }

    protected void scale(int ds) {
        int newScale = getModel().getScaleFactor() + ds;

        if (newScale < 25) newScale = 25;
        if (newScale > 1000) newScale = 10000;

        int x = getModel().getX() * newScale / getModel().getScaleFactor();
        int y = getModel().getY() * newScale / getModel().getScaleFactor();

        getModel().setScaleFactor(newScale);
        getModel().setX(x);
        getModel().setY(y);
        getModel().hasChanged();
    }

    private void doCommand(ApplicationCommands command) {
        switch (command) {
            case MOVE_UP -> move(0, -20);
            case MOVE_DOWN -> move(0, 20);
            case MOVE_LEFT -> move(-20, 0);
            case MOVE_RIGHT -> move(20, 0);
            case SCALE_OUT -> scale(10);
            case SCALE_IN -> scale(-10);
        }
    }

}
