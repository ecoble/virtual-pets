package ui.component;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ui.Compositor;

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
    protected void quit()
    {
        Stage stage = (Stage) quit.getScene().getWindow();
        stage.close();
    }
}
