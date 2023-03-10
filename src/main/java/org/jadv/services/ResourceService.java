package org.jadv.services;

import java.io.IOException;
import java.io.InputStream;

/**
 * Class to load a resource.
 * @param <R> Type of the resource to load.
 */
public abstract class ResourceService<R> {

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

    protected abstract R createResource(InputStream inputStream) throws IOException;

    /**
     * Loads the Resource.
     * @param resourceName Name of the resource to load.
     * @return The Resource R if loaded correctly or null.
     */
    public R loadResource(String resourceName) {
        String resourcePath = prefix + resourceName + type;
        try (InputStream inputStream = getClass().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                return null;
            }

            return createResource(inputStream);
        } catch (IOException exception) {
            System.out.println("Unable to load level resource: " + resourcePath + " (" + exception.getMessage() + ")");
            exception.printStackTrace();
        }
        return null;
    }
}
