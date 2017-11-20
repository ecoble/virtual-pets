package ui.component.menus;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import ui.component.Component;
import ui.component.environments.LivingRoom;
import ui.component.Root;

/**
 * Created by M5sp on 11/20/17.
 */
public class Gym extends HBox
{
    private Root root;

    @FXML
    private Button continueTraining;

    @FXML
    private Button goHome;

    public Gym (Root root)
    {
        this.root = root;
        Component.load("Gym.fxml", this);
    }

    @FXML
    protected void initialize()
    {
        root.changeMessage("You're training " + root.currPetName + ". What would you like to do?");
    }

    @FXML
    protected void continueTraining()
    {
        root.changeMessage("You and " + root.currPetName + " continue to train. What would you like to do?" );
    }

    @FXML
    protected void goHome()
    {
        root.changeMessage("You and " + root.currPetName + " returned home. What would you like to do now?");
        root.transitionDisplay(new LivingRoom(root.user));
        root.transitionMenu(new Home(root));
    }
}
