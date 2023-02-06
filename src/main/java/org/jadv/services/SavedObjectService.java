package org.jadv.services;

import com.google.gson.Gson;
import org.jadv.model.SavedObject;
import org.jadv.model.level.Level;

import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Service to load SavedObject of a resource.
 */
public class SavedObjectService extends ResourceService<Level> {

    /**
     * type of level resources.
     */
    private static final String LEVEL_RESOURCE_TYPE = ".json";

    /**
     * Resource path of level files.
     */
    private static final String LEVEL_RESOURCE_PATH = "/level/";

    /**
     * Creates a new instance of SavedObjectService.
     */
    public SavedObjectService() {
        super(LEVEL_RESOURCE_PATH, LEVEL_RESOURCE_TYPE);
    }

    /**
     * Loads a Level of an InputStream
     * @param inputStream InputStream to load Level from.
     * @return Loaded level.
     */
    protected Level loadLevel(InputStream inputStream) {
        Gson gson = new Gson();
        return (Level) gson.fromJson(new InputStreamReader(inputStream), SavedObject.class);
    }

    /**
     * Loads a Level of a given resource name.
     *
     * @param resourceName Name of the resource.
     * @return The level if resource was found, else null.
     */
    public Level loadLevel(String resourceName) {
        return loadResource(resourceName, this::loadLevel);
    }
}