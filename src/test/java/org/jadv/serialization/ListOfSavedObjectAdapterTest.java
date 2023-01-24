package org.jadv.serialization;

import com.google.gson.Gson;
import org.jadv.model.SavedObject;
import org.jadv.model.level.Level;
import org.jadv.model.objects.GameObject;
import org.jadv.model.size.RectangleSize;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListOfSavedObjectAdapterTest {

    @Test
    public void testSerialization() {
        String jsonLevel = "{\"name\":\"Testlevel\",\"width\":1000,\"height\":1000,\"objects\":[{\"name\":\"Testobject\",\"size\":{\"weidth\":10,\"height\":10,\"type\":\"org.jadv.model.level.size.RectangleSize\"},\"position\":{\"x\":1,\"y\":2},\"type\":\"org.jadv.model.level.objects.GameObject\"},{\"name\":\"Testobject2\",\"size\":{\"weidth\":10,\"height\":10,\"type\":\"org.jadv.model.level.size.RectangleSize\"},\"position\":{\"x\":2,\"y\":3},\"type\":\"org.jadv.model.level.objects.GameObject\"}],\"type\":\"org.jadv.model.level.level.Level\"}";
        Level level = new Level("Testlevel", 1000, 1000);
        GameObject obj = GameObject.builder()
                .name("Testobject")
                .size(new RectangleSize(10,10))
                .build();
        level.addObject(obj, 1, 2);
        obj = GameObject.builder()
                .name("Testobject2")
                .size(new RectangleSize(10,10))
                .build();
        level.addObject(obj, 2, 3);

        Gson gson = new Gson();
        String json = gson.toJson(level, Level.class);

        assertEquals(jsonLevel, json);
    }

    @Test
    public void testDeserialization() {

        String jsonLevel = "{\"name\":\"Testlevel\",\"width\":1000,\"height\":1000,\"objects\":[{\"name\":\"Testobject\",\"size\":{\"weidth\":10,\"height\":10,\"type\":\"org.jadv.model.level.size.RectangleSize\"},\"position\":{\"x\":1,\"y\":2},\"type\":\"org.jadv.model.level.objects.GameObject\"},{\"name\":\"Testobject2\",\"size\":{\"weidth\":10,\"height\":10,\"type\":\"org.jadv.model.level.size.RectangleSize\"},\"position\":{\"x\":2,\"y\":3},\"type\":\"org.jadv.model.level.objects.GameObject\"}],\"type\":\"org.jadv.model.level.level.Level\"}";

        Gson gson = new Gson();
        Level level2 = (Level) gson.fromJson(jsonLevel, SavedObject.class);

        assertAll(
                () -> assertEquals("Testlevel", level2.getName()),
                () -> assertEquals(2, level2.getObjects().size()),
                () -> assertEquals("Testobject", level2.getObjects().get(0).getName()),
                () -> assertEquals(1, level2.getObjects().get(0).getPosition().getX()),
                () -> assertEquals(2, level2.getObjects().get(0).getPosition().getY()),
                () -> assertEquals("Testobject2", level2.getObjects().get(1).getName()),
                () -> assertEquals(2, level2.getObjects().get(1).getPosition().getX()),
                () -> assertEquals(3, level2.getObjects().get(1).getPosition().getY())

        );

    }
}