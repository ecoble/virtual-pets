package ui.component.menus;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import ui.component.*;
import ui.component.environments.GymEnvironment;
import ui.component.environments.WalkEnvironment;
import ui.component.environments.WashEnvironment;

/**
 * Created by M5sp on 10/18/17.
 */
public class Interact extends HBox
{
    private Root root;

    public Interact(Root root)
    {
        this.root = root;
        Component.load("Interact.fxml", this);
    }

    @FXML
    protected void interact(MouseEvent event)
    {
        root.transitionMenu(new Home(root));
        Button b = (Button) event.getSource();
        root.changeMessage(b.getUserData().toString() + root.currPetName + "! What would you like to do now?");
    }

    @FXML
    protected void walk(MouseEvent event)
    {
        root.transitionDisplay(new WalkEnvironment(root));
        root.transitionMenu(new Walk(root));
    }

    @FXML
    protected void wash(MouseEvent event)
    {
        root.transitionDisplay(new WashEnvironment(root));
        root.transitionMenu(new Wash(root));
    }

    @FXML
    protected void train(MouseEvent event)
    {
        root.transitionDisplay(new GymEnvironment(root));
        root.transitionMenu(new Gym(root));
    }
}
