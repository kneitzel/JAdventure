package org.jadv.serialization;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import org.jadv.model.SavedObject;
import org.jadv.model.size.RectangleSize;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the SavedObjectAdapter
 */
public class SavedObjectAdapterTest {

    /**
     * Test serialization of a SavedObject instance.
     */
    @Test
    public void testSerialization() {
        String jsonRSize = "{\"width\":123,\"height\":50,\"type\":\"org.jadv.model.size.RectangleSize\"}";
        RectangleSize rSize = new RectangleSize(123, 50);
        Gson gson = new Gson();
        assertEquals(jsonRSize, gson.toJson(rSize, RectangleSize.class));
    }

    /**
     * Test deserialization of a SavedObject instance.
     */
    @Test
    public void testDeserialization() {
        String jsonRSize = "{\"width\":123,\"height\":50,\"type\":\"org.jadv.model.size.RectangleSize\"}";
        Gson gson = new Gson();
        RectangleSize rSize = (RectangleSize) gson.fromJson(jsonRSize, SavedObject.class);
        assertAll(
                () -> assertEquals(123, rSize.getWidth()),
                () -> assertEquals(50, rSize.getHeight())
        );
    }

    /**
     * Test thrown exception if given type is unknown.
     */
    @Test
    public void testExceptionWhenClassUnknown() {
        String json = "{\"type\":\"does.not.exist.NonExistingClass\"}";
        Gson gson = new Gson();
        assertThrows(JsonParseException.class, () -> gson.fromJson(json, SavedObject.class));
    }
}