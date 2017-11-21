package ui.component;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

/**
 * Created by M5sp on 11/1/17.
 */
public class Component
{
    public static void load(String fxmlPath, Object controller)
    {
        FXMLLoader loader = new FXMLLoader(controller.getClass().getResource(fxmlPath));
        loader.setRoot(controller);
        loader.setController(controller);

        try
        {
            loader.load();
        }
        catch (IOException exception)
        {
            throw new RuntimeException();
        }
    }
}
