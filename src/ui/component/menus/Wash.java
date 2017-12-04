package ui.component.menus;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import model.Pet;
import model.User;
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
    private User user;
    private Pet pet;

    @FXML
    private Button continueWash;

    @FXML
    private Button finishWash;

    public Wash (Root root, User user, Pet pet)
    {
        this.root = root;
        this.user = user;
        this.pet = pet;
        Component.load("Wash.fxml", this);
    }

    @FXML
    protected void initialize()
    {
        root.changeMessage("You're washing " + pet.getName() + ". What would you like to do?");
    }

    @FXML
    protected void continueWash()
    {
        root.changeMessage("You continue to wash " + pet.getName() + ". What would you like to do?" );
    }

    @FXML
    protected void finishWash()
    {
        root.changeMessage("You finished washing " + pet.getName() + ". What would you like to do now?");
        root.transitionDisplay(new LivingRoom(user));
        root.transitionMenu(new Home(root, user));
    }
}
