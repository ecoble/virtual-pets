package ui.component.menus;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import ui.component.Component;
import ui.component.Root;
import ui.component.environments.LivingRoom;
import ui.component.menus.Home;

/**
 * Created by M5sp on 11/13/17.
 */
public class Wash extends HBox
{
    private Root root;

    @FXML
    private Button continueWash;

    @FXML
    private Button finishWash;

    public Wash (Root root)
    {
        this.root = root;
        Component.load("Wash.fxml", this);
    }

    @FXML
    protected void initialize()
    {
        root.changeMessage("You're washing " + root.currPetName + ". What would you like to do?");
    }

    @FXML
    protected void continueWash()
    {
        root.changeMessage("You continue to wash " + root.currPetName + ". What would you like to do?" );
    }

    @FXML
    protected void finishWash()
    {
        root.changeMessage("You finished washing " + root.currPetName + ". What would you like to do now?");
        root.transitionDisplay(new LivingRoom(root.user));
        root.transitionMenu(new Home(root));
    }
}
