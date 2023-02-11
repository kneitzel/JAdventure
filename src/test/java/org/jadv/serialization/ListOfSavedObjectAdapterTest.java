package org.jadv.serialization;

import com.google.gson.Gson;
import org.jadv.model.SavedObject;
import org.jadv.model.level.Level;
import org.jadv.model.objects.GameObject;
import org.jadv.model.size.RectangleSize;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the ListOfSavedObjectAdapter.
 */
public class ListOfSavedObjectAdapterTest {

    /**
     * Tests the serialization of an Object (using class Level) that has a list of SavedObject.
     */
    @Test
    public void testSerialization() {
        String jsonLevel = "{\"name\":\"TestLevel\",\"width\":1000,\"height\":1000,\"graphicResource\":\"testlevel\",\"objects\":[{\"name\":\"TestObject\",\"size\":{\"width\":10,\"height\":10,\"type\":\"org.jadv.model.size.RectangleSize\"},\"position\":{\"x\":1,\"y\":2},\"type\":\"org.jadv.model.objects.GameObject\"},{\"name\":\"TestObject2\",\"size\":{\"width\":10,\"height\":10,\"type\":\"org.jadv.model.size.RectangleSize\"},\"position\":{\"x\":2,\"y\":3},\"type\":\"org.jadv.model.objects.GameObject\"}],\"type\":\"org.jadv.model.level.Level\"}";
        Level level = new Level("TestLevel", 1000, 1000, "testlevel");
        GameObject obj = GameObject.builder()
                .name("TestObject")
                .size(new RectangleSize(10,10))
                .build();
        level.addObject(obj, 1, 2);
        obj = GameObject.builder()
                .name("TestObject2")
                .size(new RectangleSize(10,10))
                .build();
        level.addObject(obj, 2, 3);

        Gson gson = new Gson();
        String json = gson.toJson(level, Level.class);

        assertEquals(jsonLevel, json);
    }

    /**
     * Tests the deserialization of an Object (using class Level) that has a list of SavedObject.
     */
    @Test
    public void testDeserialization() {

        String jsonLevel = "{\"name\":\"TestLevel\",\"width\":1000,\"height\":1000,\"graphicResource\":\"testlevel\",\"objects\":[{\"name\":\"TestObject\",\"size\":{\"width\":10,\"height\":10,\"type\":\"org.jadv.model.size.RectangleSize\"},\"position\":{\"x\":1,\"y\":2},\"type\":\"org.jadv.model.objects.GameObject\"},{\"name\":\"TestObject2\",\"size\":{\"width\":10,\"height\":10,\"type\":\"org.jadv.model.size.RectangleSize\"},\"position\":{\"x\":2,\"y\":3},\"type\":\"org.jadv.model.objects.GameObject\"}],\"type\":\"org.jadv.model.level.Level\"}";

        Gson gson = new Gson();
        Level level2 = (Level) gson.fromJson(jsonLevel, SavedObject.class);

        assertAll(
                () -> assertEquals("TestLevel", level2.getName()),
                () -> assertEquals(2, level2.getObjects().size()),
                () -> assertEquals("TestObject", level2.getObjects().get(0).getName()),
                () -> assertEquals(1, level2.getObjects().get(0).getPosition().getX()),
                () -> assertEquals(2, level2.getObjects().get(0).getPosition().getY()),
                () -> assertEquals(level2, level2.getObjects().get(0).getPosition().getParent()),
                () -> assertEquals("TestObject2", level2.getObjects().get(1).getName()),
                () -> assertEquals(2, level2.getObjects().get(1).getPosition().getX()),
                () -> assertEquals(3, level2.getObjects().get(1).getPosition().getY()),
                () -> assertEquals(level2, level2.getObjects().get(1).getPosition().getParent())
        );
    }
}