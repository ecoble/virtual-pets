package ui.component;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * Created by M5sp on 11/13/17.
 */
public class Walk extends HBox
{
    private Root root;

    @FXML
    private Button continueWalk;

    @FXML
    private Button goHome;

    public Walk (Root root)
    {
        this.root = root;
        Component.load("Walk.fxml", this);
    }

    @FXML
    protected void initialize()
    {
        root.changeMessage("You're walking " + root.currPetName + ". What would you like to do?");
    }

    @FXML
    protected void continueWalk()
    {
        root.changeMessage("You and " + root.currPetName + " continue to walk. What would you like to do?" );
    }

    @FXML
    protected void goHome()
    {
        root.changeMessage("You and " + root.currPetName + " returned home. What would you like to do now?");
        root.transitionDisplay(new LivingRoom(root.user));
        root.transitionMenu(new Home(root));
    }
}
