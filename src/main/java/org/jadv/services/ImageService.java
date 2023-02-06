package org.jadv.services;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * Service to provide images.
 */
public class ImageService {

    /**
     * Path where we find image resources.
     */
    public static final String IMAGE_RESOURCE_PATH = "/images/";

    /**
     * File type of image resources.
     */
    public static final String IMAGE_RESOURCE_TYPE = ".png";

    /**
     * Gets an image for a resource name.
     * @param resourceName Name of the resource.
     * @return Image of the resource of null if it could not be loaded.
     */
    public Image loadImage(String resourceName) {
        String resourcePath = IMAGE_RESOURCE_PATH + resourceName + IMAGE_RESOURCE_TYPE;
        try (InputStream inputStream = getClass().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                return null;
            }
            return ImageIO.read(inputStream);
        } catch (IOException exception) {
            System.out.println("Unable to load resource: " + resourcePath + " (" + exception.getMessage() + ")");
            exception.printStackTrace();
        }
        return null;
    }
}
