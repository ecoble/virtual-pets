package model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by M5sp on 2/27/18.
 */
public class ObservableListAdapter<T> implements JsonDeserializer<ObservableList<T>>
{
    private Class<T> itemType;

    public ObservableListAdapter(Class<T> itemType)
    {
        this.itemType = itemType;
    }


    @Override
    public ObservableList<T> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException
    {
        ObservableList<T> list = FXCollections.observableArrayList();

        for (JsonElement el : jsonElement.getAsJsonArray())
        {
            list.add(jsonDeserializationContext.deserialize(el, itemType));
        }

        return list;
    }
}
