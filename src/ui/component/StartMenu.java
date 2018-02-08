package ui.component;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

/**
 * Created by M5sp on 2/7/18.
 */
public class StartMenu extends VBox
{
    public StartMenu()
    {
        Component.load("StartMenu.fxml", this);
    }

    @FXML
    protected void start()
    {

    }
}
