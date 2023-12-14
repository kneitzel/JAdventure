package org.jadv.services;

import org.jadv.model.level.Level;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LevelServiceTest {
    /**
     * Tests loading of a test level resource.
     */
    @Test
    void testLoadingLevel() {
        LevelService levelService = new LevelService();

        Level level =  levelService.loadLevel("testlevel");

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
    void testFailedLoading() {
        LevelService levelService = new LevelService();

        Level level =  levelService.loadLevel("non/existing");

        assertNull(level);
    }
}