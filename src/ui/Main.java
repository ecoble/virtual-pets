package ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Dog;
import model.Pet;
import model.DoublePropertyAdapter;
import ui.component.StartMenu;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        VBox startMenu = new VBox();

        Compositor compositor = new Compositor(startMenu);
        StartMenu start = new StartMenu();
        compositor.transitionTo(start);

        primaryStage.setTitle("Start Virtual Pets");
        primaryStage.setScene(new Scene(startMenu, 300, 200));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
