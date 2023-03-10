package org.jadv.services;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * Service to provide images.
 */
public class ImageService extends ResourceService<Image>{

    /**
     * Path where we find image resources.
     */
    public static final String IMAGE_RESOURCE_PATH = "/images/";

    /**
     * File type of image resources.
     */
    public static final String IMAGE_RESOURCE_TYPE = ".png";

    /**
     * Creates a new instance of ImageService.
     */
    public ImageService() {
        super(IMAGE_RESOURCE_PATH, IMAGE_RESOURCE_TYPE);
    }

    /**
     * Gets an image for a resource name.
     * @param resourceName Name of the resource.
     * @return Image of the resource of null if it could not be loaded.
     */
    public Image loadImage(String resourceName) {
        return loadResource(resourceName);
    }

    /**
     * Creates an instance of the requested resource.
     * @param inputStream Stream to read the data from.
     * @return The requested instance.
     * @throws IOException OException is thrown if the image cannot be loaded.
     */
    @Override
    protected Image createResource(InputStream inputStream) throws IOException {
        return ImageIO.read(inputStream);
    }
}
