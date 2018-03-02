package ui.component;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Json;
import model.User;
import ui.Compositor;

import java.io.FileReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by M5sp on 2/7/18.
 */
public class StartMenu extends VBox
{

    @FXML
    private Button start;

    @FXML
    private Button quit;

    public StartMenu()
    {
        Component.load("StartMenu.fxml", this);
    }

    @FXML
    protected void start()
    {
        Stage stage = (Stage) start.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage();
        VBox root = new VBox();

        Compositor compositor = new Compositor(root);
        Root rootMenu = new Root();

        compositor.transitionTo(rootMenu);

        primaryStage.setTitle("Virtual Pets");
        primaryStage.setScene(new Scene(root, 650, 650));
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    @FXML
    protected void load()
    {
        try
        {
            byte[] encoded = Files.readAllBytes(Paths.get("./save.json"));
            String json = new String(encoded, Charset.defaultCharset());
            User user = Json.from(json, User.class);

            Stage stage = (Stage) start.getScene().getWindow();
            stage.close();

            Stage primaryStage = new Stage();
            VBox root = new VBox();

            Compositor compositor = new Compositor(root);
            Root rootMenu = new Root(user);

            compositor.transitionTo(rootMenu);

            primaryStage.setTitle("Virtual Pets");
            primaryStage.setScene(new Scene(root, 650, 650));
            primaryStage.show();
            primaryStage.setResizable(false);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    @FXML
    protected void quit()
    {
        Platform.exit();
    }
}
