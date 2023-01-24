package org.jadv.serialization;

import com.google.gson.Gson;
import org.jadv.model.SavedObject;
import org.jadv.model.size.RectangleSize;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the SavedObjectAdapter
 */
public class SavedObjectAdapterTest {

    @Test
    public void testSerialization() {
        String jsonRSize = "{\"width\":123,\"height\":50,\"type\":\"org.jadv.model.size.RectangleSize\"}";
        RectangleSize rSize = new RectangleSize(123, 50);
        Gson gson = new Gson();
        assertEquals(jsonRSize, gson.toJson(rSize, RectangleSize.class));
    }

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
}