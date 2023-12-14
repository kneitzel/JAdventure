package org.jadv.client;

import org.jadv.model.level.Level;
import org.jadv.model.objects.GameObject;
import org.jadv.services.ImageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Tests the ImageStore class.
 */
@ExtendWith(MockitoExtension.class)
class ImageStoreTest {

    /**
     * Tests the checkAndLoadImages method of ImageStore.
     */
    @Test
    void loadingImagesOfLevel() {
        Image testImage1 = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
        Image testImage2 = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
        ImageService imageMockService = Mockito.mock(ImageService.class);
        when(imageMockService.loadImage("existing1")).thenReturn(testImage1);
        when(imageMockService.loadImage("existing2")).thenReturn(testImage2);

        Level level = Mockito.mock(Level.class);
        when(level.getGraphicResource()).thenReturn("existing1");
        when(level.getChildren()).thenReturn(List.of(GameObject.builder().graphicResource("existing2").build()));

        ImageStore store = new ImageStore(imageMockService);
        store.checkAndLoadImages(level);

        assertAll(
                () -> assertTrue(store.hasImage("existing1")),
                () -> assertTrue(store.hasImage("existing2")),
                () -> assertEquals(testImage1, store.getImage("existing1")),
                () -> assertEquals(testImage2, store.getImage("existing2"))
        );
    }

    /**
     * Tests the checkAndLoadImages method of ImageStore with a Level which has a GameObject inside without existing resource.
     */
    @Test
    void loadingImagesOfLevelWithProblemAtChild() {
        Image testImage = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
        ImageService imageMockService = Mockito.mock(ImageService.class);
        when(imageMockService.loadImage("existing")).thenReturn(testImage);
        when(imageMockService.loadImage("non/existing")).thenReturn(null);

        Level level = Mockito.mock(Level.class);
        when(level.getGraphicResource()).thenReturn("existing");
        when(level.getChildren()).thenReturn(List.of(GameObject.builder().graphicResource("non/existing").build()));

        ImageStore store = new ImageStore(imageMockService);
        store.checkAndLoadImages(level);

        assertAll(
                () -> assertTrue(store.hasImage("existing")),
                () -> assertEquals(testImage, store.getImage("existing")),
                () -> assertFalse(store.hasImage("non/existing")),
                () -> assertNull(store.getImage("non/existing"))
        );
    }

    /**
     * Tests loading of a single image.
     */
    @Test
    void testCheckAndLoadSingleImage() {
        Image testImage = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
        ImageService imageMockService = Mockito.mock(ImageService.class);
        when(imageMockService.loadImage("existing")).thenReturn(testImage);

        ImageStore store = new ImageStore(imageMockService);
        store.checkAndLoadImage("existing");

        assertAll(
                () -> assertEquals(testImage, store.getImage("existing")),
                () -> assertTrue(store.hasImage("existing"))
        );

    }
}