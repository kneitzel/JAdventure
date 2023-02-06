package org.jadv.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Function;

/**
 * Class to load a resource.
 * @param <R> Type of the resource to load.
 */
public class ResourceService<R> {

    /**
     * Prefix of the resource to load.
     */
    private final String prefix;

    /**
     * Type of the resource to load.
     */
    private final String type;

    /**
     * Creates a new instance of ResourceService.
     * @param prefix Prefix of the resource e.g. "/images/".
     * @param type Type of the resource to load e.g. ".json".
     */
    public ResourceService(String prefix, String type) {
        this.prefix = prefix;
        this.type = type;
    }

    /**
     * Loads the Resource.
     * @param resourceName Name of the resource to load.
     * @param function Function that takes an InputStream and returns a Resource of the reqiured Type R.
     * @return The Resource R if loaded correctly or null.
     */
    public R loadResource(String resourceName, Function<InputStream, R> function) {
        String resourcePath = prefix + resourceName + type;
        try (InputStream inputStream = getClass().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                return null;
            }

            return function.apply(inputStream);
        } catch (IOException exception) {
            System.out.println("Unable to load level resource: " + resourcePath + " (" + exception.getMessage() + ")");
            exception.printStackTrace();
        }
        return null;
    }
}
