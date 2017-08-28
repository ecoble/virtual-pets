package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Virtual Pets");
        primaryStage.setScene(new Scene(root, 500, 350));
        primaryStage.show();

        //Image image = new Image("http://www.impressiveinteriordesign.com/wp-content/uploads/2012/09/Photos-Of-Modern-Living-Room-Interior-Design-Ideas-0.jpg");
        //ImageView imv = new ImageView();
        //imv.setImage(image);




    }


    public static void main(String[] args) {
        launch(args);
    }
}
