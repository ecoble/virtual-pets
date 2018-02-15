package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import javafx.beans.property.DoubleProperty;

/**
 * Created by M5sp on 2/14/18.
 */
public class Json
{
    private static final RuntimeTypeAdapterFactory<Pet> factory = RuntimeTypeAdapterFactory
            .of(Pet.class)
            .registerSubtype(Dog.class)
            .registerSubtype(Cat.class)
            .registerSubtype(Bird.class)
            .registerSubtype(Fish.class)
            .registerSubtype(Rabbit.class);

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(factory)
            .registerTypeAdapter(DoubleProperty.class, new DoublePropertyAdapter())
            .create();

    public static String to(Object obj)
    {
        return gson.toJson(obj);
    }

    public static <T> T from(String json, Class<T> classOfT)
    {
        return gson.fromJson(json, classOfT);
    }


}
