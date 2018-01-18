package ui.component.menus;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import model.Pet;
import model.User;
import ui.component.Component;
import ui.component.Root;
import ui.component.environments.LivingRoom;
import ui.component.menus.Home;

/**
 * Created by M5sp on 11/13/17.
 */
public class Walk extends HBox
{
    private Root root;
    private User user;
    private Pet pet;

    @FXML
    private Button continueWalk;

    @FXML
    private Button goHome;

    public Walk (Root root, User user, Pet pet)
    {
        this.root = root;
        this.user = user;
        this.pet = pet;
        Component.load("Walk.fxml", this);
    }

    @FXML
    protected void initialize()
    {
        switch(pet.getSpecies())
        {
            case "dog":
                root.changeMessage("You're walking " + pet.getName() + ". What would you like to do?");
                break;
            case "cat":
                root.changeMessage("You're walking " + pet.getName() + ", but they're getting upset. What would you like to do?");
                break;
            case "rabbit":
                root.changeMessage("You're walking " + pet.getName() + ", but it is not going well. What would you like to do?");
                break;
            case "fish":
                root.changeMessage("You're walking your fish, " + pet.getName() + ", but not for long.");
                losePet();
                break;
            case "bird":
                root.changeMessage("You tried to walk your bird, " + pet.getName() + ", but they're flying away! Oh no!");
                losePet();
                break;
        }
    }

    @FXML
    protected void continueWalk()
    {
        root.changeMessage("You and " + pet.getName() + " continue to walk. What would you like to do?" );
    }

    @FXML
    protected void goHome()
    {
        root.changeMessage("You and " + pet.getName() + " returned home. What would you like to do now?");
        root.transitionDisplay(new LivingRoom(user));
        root.transitionMenu(new Home(root, user));
    }

    private void losePet()
    {
        continueWalk.setVisible(false);
        goHome.setVisible(false);

        new Timeline(new KeyFrame(
                Duration.millis(3000),
                ae -> {
                    root.transitionMenu(new Home(root,user));
                    root.transitionDisplay(new LivingRoom(user));

                    if (pet.getSpecies().equals("fish"))
                    {
                        root.changeMessage(pet.getName() + " died due to lack of water.");
                    }
                    else if (pet.getSpecies().equals("bird"))
                    {
                        root.changeMessage(pet.getName() + " flew away into the great beyond.");
                    }
                })
        ).play();

        user.removePet(this.pet);

    }
}
