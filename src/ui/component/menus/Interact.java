package ui.component.menus;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Pet;
import model.User;
import ui.component.*;
import ui.component.environments.CompetitionEnvironment;
import ui.component.environments.GymEnvironment;
import ui.component.environments.WalkEnvironment;
import ui.component.environments.WashEnvironment;

/**
 * Created by M5sp on 10/18/17.
 */
public class Interact extends VBox
{
    private Root root;
    private User user;
    private Pet pet;


    public Interact(Root root, User user, Pet pet)
    {
        this.root = root;
        this.user = user;
        this.pet = pet;
        Component.load("Interact.fxml", this);
    }

    @FXML
    protected void walk(MouseEvent event)
    {
        root.clearMenu();
        root.transitionDisplay(new WalkEnvironment(root,user, pet));
        //root.transitionMenu(new Walk(root, user, pet));
    }

    @FXML
    protected void wash(MouseEvent event)
    {
        root.clearMenu();
        root.transitionDisplay(new WashEnvironment(root, user, pet));
        //root.transitionMenu(new Wash(root, user, pet));
    }

    @FXML
    protected void train(MouseEvent event)
    {
        root.clearMenu();
        root.transitionDisplay(new GymEnvironment(root, user, pet));
        //root.transitionMenu(new Gym(root, user, pet));
    }

    @FXML
    protected void feed(MouseEvent event)
    {
        if(user.getFood(pet.getFoodType()) > 0){
            pet.feed();
            user.withdrawFood(pet.getFoodType());
            root.changeMessage("You fed " + pet.getName() + "! What would you like to do now?");
        }
        else
        {
            root.changeMessage("You can't feed " + pet.getName() + ", you don't have any food! What would you like to do now?");
        }
        root.transitionMenu(new Home(root, user));

    }

    @FXML
    protected void giveWater(MouseEvent event)
    {
        pet.giveWater();
        root.transitionMenu(new Home(root, user));
        root.changeMessage("You gave water to " + pet.getName() + "! What would you like to do now?");
    }

    @FXML
    protected void cancel(MouseEvent event)
    {
        root.transitionMenu(new Home(root, user));
        root.changeMessage("What would you like to do now?");
    }

    @FXML
    protected void compete(MouseEvent event)
    {
        root.clearMenu();
        CompetitionEnvironment ce = new CompetitionEnvironment(root, user, pet);
        root.transitionDisplay(ce);
        //root.transitionMenu(new Competition(root, user, pet, ce));

}
}
