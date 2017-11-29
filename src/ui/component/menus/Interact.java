package ui.component.menus;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ui.component.*;
import ui.component.environments.GymEnvironment;
import ui.component.environments.WalkEnvironment;
import ui.component.environments.WashEnvironment;

/**
 * Created by M5sp on 10/18/17.
 */
public class Interact extends VBox
{
    private Root root;

    public Interact(Root root)
    {
        this.root = root;
        Component.load("Interact.fxml", this);
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

    @FXML
    protected void feed(MouseEvent event)
    {
        if(root.user.getFood() > 0){
            root.livingRoom.addFood();
            root.user.withdrawFood();
            root.changeMessage("You fed " + root.currPetName + "! What would you like to do now?");
        }
        else
        {
            root.changeMessage("You can't feed " + root.currPetName + ", you don't have any food! What would you like to do now?");
        }
        root.transitionMenu(new Home(root));

    }

    @FXML
    protected void giveWater(MouseEvent event)
    {
        root.livingRoom.addWater();
        root.transitionMenu(new Home(root));
        root.changeMessage("You gave water to " + root.currPetName + "! What would you like to do now?");
    }

    @FXML
    protected void cancel(MouseEvent event)
    {
        root.transitionMenu(new Home(root));
        root.changeMessage("What would you like to do now?");
    }
}
