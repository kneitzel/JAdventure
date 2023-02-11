package org.jadv.client;

import org.jadv.model.level.Level;
import org.jadv.model.objects.GameObject;
import org.jadv.services.ImageService;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ImageStore {

    private final ImageService imageService;

    /**
     * Store for all Images
     */
    private final Map<String, Image> images = new HashMap<>();

    public ImageStore(ImageService imageService) {
        this.imageService = imageService;
    }

    /**
     * Checks if an image is already inside the Map.
     * @param name Name of the image.
     * @return true if the image is inside the map, else false.
     */
    public boolean hasImage(String name) {
        return images.containsKey(name);
    }

    /**
     * Gets the image of a given name.
     * @param name Name of the image.
     * @return The image if available else null.
     */
    public Image getImage(String name) {
        return images.get(name);
    }

    /**
     * Adds an image to the store, if an image with that name already exists, then the image is replaced.
     * @param name Name of image.
     * @param image Image to put in store.
     */
    public void addImage(String name, Image image) {
        images.put(name, image);
    }

    /**
     * Checks all images used by a level and loads missing images.
     * @param level Level to check.
     */
    public void checkAndLoadImages(Level level) {
        checkAndLoadImage(level.getGraphicResource());

        for (GameObject child: level.getChildren()) {
            checkAndLoadImage(child.getGraphicResource());
        }
    }

    /**
     * Checks and loads Images if required.
     * @param name Name of the image.
     */
    public void checkAndLoadImage(String name) {
        if (hasImage(name)) return;

        Image image = imageService.loadImage(name);
        if (image != null) addImage(name, image);
    }

}
