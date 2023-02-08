package org.jadv.client;

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

    private final ImageStore imageStore;

    /**
     * Creates a new instance of ApplicationController
     * @param imageService ImageService to use to load images.
     * @param levelService LevelService to use to load level.
     */
    public ApplicationController(ImageService imageService, LevelService levelService) {
        this.imageService = imageService;
        this.levelService = levelService;

        imageStore = new ImageStore(imageService);
        ApplicationModel model = new ApplicationModel(imageStore);
        ApplicationView view = new ApplicationView(model);
        init(model, view);
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
}
