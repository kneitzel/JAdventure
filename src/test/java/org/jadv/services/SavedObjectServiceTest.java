package org.jadv.services;

import org.jadv.model.level.Level;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SavedObjectServiceTest {
    /**
     * Tests loading of a test level resource.
     */
    @Test
    public void testLoadingLevel() {
        SavedObjectService savedObjectService = new SavedObjectService();

        Level level =  savedObjectService.loadLevel("testlevel");

        assertAll(
                () -> assertNotNull(level),
                () -> assertEquals(1024, level.getWidth()),
                () -> assertEquals(1024, level.getHeight()),
                () -> assertEquals("TestLevel", level.getName())
        );
    }

    /**
     * Tests that null is given back when the resource cannot be loaded.
     */
    @Test
    public void testFailedLoading() {
        SavedObjectService savedObjectService = new SavedObjectService();

        Level level =  savedObjectService.loadLevel("non/existing");

        assertNull(level);
    }
}