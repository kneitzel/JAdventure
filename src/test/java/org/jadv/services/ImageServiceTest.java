package org.jadv.services;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the ImageService
 */
class ImageServiceTest {

    /**
     * Tests loading of a test level image with a known size.
     */
    @Test
    public void testLoadingLevel() {
        ImageService imageService = new ImageService();

        Image image =  imageService.loadImage("level/testlevel");

        assertAll(
                () -> assertNotNull(image),
                () -> assertEquals(1024, image.getWidth(null)),
                () -> assertEquals(1024, image.getHeight(null))
        );
    }

    /**
     * Tests that null is given back when the resource cannot be loaded.
     */
    @Test
    public void testFailedLoading() {
        ImageService imageService = new ImageService();

        Image image =  imageService.loadImage("non/existing");

        assertNull(image);
    }
}