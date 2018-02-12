package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ui.component.Root;
import ui.component.StartMenu;

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
        //VBox root = new VBox();
//
        //Compositor compositor = new Compositor(root);
        //Root rootMenu = new Root();
//
        //compositor.transitionTo(rootMenu);
//
        //primaryStage.setTitle("Virtual Pets");
        //primaryStage.setScene(new Scene(root, 650, 650));
        //primaryStage.show();
        //primaryStage.setResizable(false);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
