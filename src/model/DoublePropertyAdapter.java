package model;

import com.google.gson.*;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.lang.reflect.Type;

/**
 * Created by M5sp on 2/14/18.
 */
public class DoublePropertyAdapter implements JsonSerializer<DoubleProperty>, JsonDeserializer<DoubleProperty>
{
    @Override
    public JsonElement serialize(DoubleProperty doubleProperty, Type type, JsonSerializationContext jsonSerializationContext)
    {
        double stat = doubleProperty.get();
        return new JsonPrimitive(stat);
    }

    @Override
    public DoubleProperty deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException
    {
        return new SimpleDoubleProperty(jsonElement.getAsDouble());
    }

}
