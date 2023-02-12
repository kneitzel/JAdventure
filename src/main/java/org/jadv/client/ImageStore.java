package org.jadv.client;

import org.jadv.model.level.Level;
import org.jadv.model.objects.GameObject;
import org.jadv.services.ImageService;

import java.awt.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ImageStore holds required images with their resource names.
 */
public class ImageStore implements Serializable {

    /**
     * Serial version UID of ImageStore
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ImageService to load images.
     */
    private final transient ImageService imageService;

    /**
     * Store for all Images
     */
    private final Map<String, Image> images = new ConcurrentHashMap<>();

    /**
     * Creates a new instance of ImageStore.
     * @param imageService ImageService to use to load images.
     */
    public ImageStore(final ImageService imageService) {
        this.imageService = imageService;
    }

    /**
     * Checks if an image is already inside the Map.
     * @param name Name of the image.
     * @return true if the image is inside the map, else false.
     */
    public boolean hasImage(final String name) {
        return images.containsKey(name);
    }

    /**
     * Gets the image of a given name.
     * @param name Name of the image.
     * @return The image if available else null.
     */
    public Image getImage(final String name) {
        return images.get(name);
    }

    /**
     * Adds an image to the store, if an image with that name already exists, then the image is replaced.
     * @param name Name of image.
     * @param image Image to put in store.
     */
    public void addImage(final String name, final Image image) {
        images.put(name, image);
    }

    /**
     * Checks all images used by a level and loads missing images.
     * @param level Level to check.
     */
    public void checkAndLoadImages(final Level level) {
        checkAndLoadImage(level.getGraphicResource());

        for (final GameObject child: level.getChildren()) {
            checkAndLoadImage(child.getGraphicResource());
        }
    }

    /**
     * Checks and loads Images if required.
     * @param name Name of the image.
     */
    public void checkAndLoadImage(final String name) {
        if (hasImage(name)) {
            return;
        }

        final Image image = imageService.loadImage(name);
        if (image != null) {
            addImage(name, image);
        }
    }

}
