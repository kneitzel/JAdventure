package org.jadv.serialization;

import com.google.gson.*;
import org.jadv.model.Container;
import org.jadv.model.SavedObject;
import org.jadv.model.objects.GameObject;

import java.lang.reflect.Type;
import java.util.Objects;

/**
 * Gson Adapter to (de-)serialize derived types.
 * Just serialize / deserialize with type SavedObject to use this adapter.
 */
final public class SavedObjectAdapter implements JsonSerializer<SavedObject>, JsonDeserializer<SavedObject> {

    /**
     * Name of the element that describes the type.
     */
    public static final String TYPE_ELEMENT_NAME = "type";

    /**
     * Serializes an SavedObject to JSON. Makes sure that the serialization is using the correct class.
     * @param object Object to serialize.
     * @param interfaceType Not used.
     * @param context Serialization context.
     * @return The JsonElement that holds the serialized object.
     */
    @Override
    public JsonElement serialize(final SavedObject object, final Type interfaceType,
                                 final JsonSerializationContext context) {
        if (object == null) return null;
        return context.serialize(object, object.getClass());
    }

    /**
     * Deserializes the SavedInstance from a json.
     * @param elem Json element to deserialize
     * @param interfaceType not used.
     * @param context Deserialization context.
     * @return The restored instance with correct class.
     * @throws JsonParseException Throws an JsonParseException if deserialization is not possible.
     */
    @Override
    public SavedObject deserialize(final JsonElement elem, final Type interfaceType,
                                   final JsonDeserializationContext context) throws JsonParseException {
        final JsonObject wrapper = (JsonObject) elem;
        final JsonElement typeName = getType(wrapper);
        final Type actualType = typeForName(typeName);
        SavedObject result =  context.deserialize(elem, actualType);
        setParentReferencesIfRequired(result);
        return result;
    }

    /**
     * The parent reference inside the Position of a GameObject is transient and is not saved.
     * So when deserializing an Container, we have to set the parent again.
     * @param object SavedObject to check and set Parent if required.
     */
    private void setParentReferencesIfRequired(final SavedObject object) {
        if (object instanceof Container container) {
            container.getChilds().stream()
                    .map(GameObject::getPosition)
                    .filter(Objects::nonNull)
                    .forEach(p -> p.setParent(container));
        }
    }

    /**
     * Gets the type for a given name.
     * @param typeElem JsonElement with classname inside.
     * @return The requested class.
     * @throws JsonParseException Thrown if the class is not available / known.
     */
    private Type typeForName(final JsonElement typeElem) throws JsonParseException {
        try {
            return Class.forName(typeElem.getAsString());
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e);
        }
    }

    /**
     * Gets an child element of an JsonObject,
     * @param wrapper Wrapper JsonObject to get the element from.
     * @return The requested JsonElement.
     * @throws JsonParseException Thrown if the requested element is not available.
     */
    private JsonElement getType(final JsonObject wrapper) throws JsonParseException {
        final JsonElement elem = wrapper.get(TYPE_ELEMENT_NAME);
        if (elem == null) throw new JsonParseException("no '" + TYPE_ELEMENT_NAME + "' member found in what was expected to be an interface wrapper");
        return elem;
    }
}