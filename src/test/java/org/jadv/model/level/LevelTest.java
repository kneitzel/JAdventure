package org.jadv.model.level;

import org.jadv.model.objects.GameObject;
import org.jadv.model.size.RectangleSize;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the logic of the Level class.
 */
class LevelTest {
    /**
     * Tests the adding of an object to the level
     * <p>
     *     After adding an object to the level at a specified point, the position of the object must be correct.
     * </p>
     */
    @Test
    void testAddObject() {
        Level level = new Level("TestLevel", 1000, 1000, "testlevel");
        GameObject obj = GameObject.builder().name("TestObject").size(new RectangleSize(10, 10)).build();
        level.addObject(obj, 1, 2);
        assertAll(
                () -> assertEquals(level, level.getObjects().get(0).getPosition().getParent()),
                () -> assertEquals(1, level.getObjects().get(0).getPosition().getX()),
                () -> assertEquals(2, level.getObjects().get(0).getPosition().getY()));

    }

    /**
     * Tests that objects can be removed from a level.
     */
    @Test
    void testRemoveObject() {
        Level level = new Level("TestLevel", 1000, 1000, "testlevel");
        GameObject obj1 = GameObject.builder().name("TestObject").size(new RectangleSize(10, 10)).build();
        level.addObject(obj1, 1, 2);
        GameObject obj2 = GameObject.builder().name("TestObject2").size(new RectangleSize(10, 10)).build();
        level.addObject(obj2, 3, 4);

        level.removeObject(obj2);
        assertEquals(1, level.getObjects().size());
    }

    /**
     * Tests remove does not throw an exception on Object that is not inside the Instance.
     */
    @Test
    void testRemoveOfNotAddedObject() {
        Level level = new Level("TestLevel", 1000, 1000, "testlevel");
        GameObject obj1 = GameObject.builder().name("TestObject").size(new RectangleSize(10, 10)).build();
        level.addObject(obj1, 1, 2);

        GameObject obj2 = GameObject.builder().name("TestObject2").size(new RectangleSize(10, 10)).build();
        level.removeObject(obj2);

        assertEquals(1, level.getObjects().size());
    }

    /**
     * Tests the getObjects method.
     */
    @Test
    void testGetChildren() {
        Level level = new Level("TestLevel", 1000, 1000, "testlevel");
        GameObject obj = GameObject.builder().name("TestObject").size(new RectangleSize(10, 10)).build();
        level.addObject(obj, 1, 2);

        assertEquals(level.getObjects(), level.getChildren());
    }
}