package ui.component.menus;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import model.Pet;
import model.User;
import ui.component.Component;
import ui.component.environments.LivingRoom;
import ui.component.Root;

/**
 * Created by M5sp on 11/20/17.
 */
public class Gym extends HBox
{
    private Root root;
    private User user;
    private Pet pet;

    @FXML
    private Button continueTraining;

    @FXML
    private Button goHome;

    public Gym (Root root, User user, Pet pet)
    {
        this.root = root;
        this.user = user;
        this.pet = pet;
        Component.load("Gym.fxml", this);
    }

    @FXML
    protected void initialize()
    {
        root.changeMessage("You're training " + pet.getName() + ". What would you like to do?");
    }

    @FXML
    protected void continueTraining()
    {
        root.changeMessage("You and " + pet.getName() + " continue to train. What would you like to do?" );
    }

    @FXML
    protected void goHome()
    {
        root.changeMessage("You and " + pet.getName() + " returned home. What would you like to do now?");
        root.transitionDisplay(new LivingRoom(user));
        root.transitionMenu(new Home(root, user));
    }
}
