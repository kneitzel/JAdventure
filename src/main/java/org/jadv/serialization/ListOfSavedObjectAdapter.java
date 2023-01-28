package org.jadv.serialization;

import com.google.gson.*;
import org.jadv.model.SavedObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Gson Adapter to (de-)serialize a List of SavedObjects
 * Prefix the List with @JsonAdapter(ListOfSavedObjectAdapter.class) to use this Adapter
 */
final public class ListOfSavedObjectAdapter implements JsonSerializer<List<? extends SavedObject>>, JsonDeserializer<List<? extends SavedObject>> {
    /**
     * Serializes a list of SavedInstance to JSON.
     * @param list Object to serialize.
     * @param interfaceType Not used.
     * @param context Serialization context.
     * @return The JsonElement that holds the serialized list.
     */
    public JsonElement serialize(final List<? extends SavedObject> list, final Type interfaceType,
                                 final JsonSerializationContext context) {
        if (list == null) return null;
        final JsonArray array = new JsonArray();
        for (SavedObject obj : list) {
            array.add(context.serialize(obj, SavedObject.class));
        }
        return array;
    }

    /**
     * Deserializes the list of SavedInstance from a json.
     * @param elem Json element to deserialize
     * @param interfaceType not used.
     * @param context Deserialization context.
     * @return The restored list with elements with correct class.
     * @throws JsonParseException Throws an JsonParseException if deserialization is not possible.
     */
    public List<? extends SavedObject> deserialize(final JsonElement elem, final Type interfaceType,
                                         final JsonDeserializationContext context) throws JsonParseException {
        List<SavedObject> result = new ArrayList<>();
        final JsonArray array = (JsonArray) elem;
        for (JsonElement element : array.asList()) {
            result.add(context.deserialize(element, SavedObject.class));
        }
        return result;
    }
}