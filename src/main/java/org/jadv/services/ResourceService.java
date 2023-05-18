package org.jadv.services;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;

/**
 * Class to load a resource.
 * @param <R> Type of the resource to load.
 */
@Log4j2
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
    public ResourceService(final String prefix, final String type) {
        this.prefix = prefix;
        this.type = type;
    }

    /**
     * Creates a Resource of generic type R from an input stream.
     * @param inputStream Input stream to read data for requested resource.
     * @return The resource.
     * @throws IOException IOEception when data cannot be read.
     */
    protected abstract R createResource(InputStream inputStream) throws IOException;

    /**
     * Loads the Resource.
     * @param resourceName Name of the resource to load.
     * @return The Resource R if loaded correctly or null.
     */
    public R loadResource(final String resourceName) {
        log.info(MessageFormat.format("Loading resource {0}", resourceName));
        final String resourcePath = prefix + resourceName + type;
        try (InputStream inputStream = getClass().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                log.warn(MessageFormat.format("Resource {0} not found!", resourcePath));
                return null;
            }

            return createResource(inputStream);
        } catch (IOException exception) {
            log.error(MessageFormat.format("Unable to load level resource: {0}", resourcePath), exception);
        }
        return null;
    }
}
