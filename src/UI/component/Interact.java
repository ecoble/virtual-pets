package ui.component;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

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
}
