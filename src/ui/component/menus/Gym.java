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
        switch(pet.getSpecies())
        {
            case "dog":
                root.changeMessage("You're training " + pet.getName() + ". They are learning a lot! What would you like to do?");
                break;
            case "cat":
                root.changeMessage("You're training " + pet.getName() + ", but they don't seem interested. What would you like to do?");
                break;
            case "rabbit":
                root.changeMessage("You're training " + pet.getName() + ". What would you like to do?");
                break;
            case "fish":
                root.changeMessage("You're training your fish, " + pet.getName() + ", but not for long!");
                losePet();
                break;
            case "bird":
                root.changeMessage("You're training your bird, " + pet.getName() + ". They are learning a lot! What would you like to do?");
                break;
        }

        pet.hungerStatProperty().addListener((change ->
        {
            if (pet.getHungerStat() <= 0)
            {
                user.removePet(pet);
                root.transitionDisplay(new LivingRoom(user, root));
                root.transitionMenu(new Home(root, user));
            }
        }));

        pet.thirstStatProperty().addListener((change -> {
            if(pet.getThirstStat() <= 0)
            {
                user.removePet(pet);
                root.transitionDisplay(new LivingRoom(user, root));
                root.transitionMenu(new Home(root, user));
            }
        }));

        pet.hygieneStatProperty().addListener((changeStat ->
        {
            if (pet.getHygieneStat() == 0)
            {
                root.changeMessage(pet.getName() + " is very dirty! You should give them a bath!");
            }
        }));
    }

    @FXML
    protected void continueTraining()
    {
        root.changeMessage("You and " + pet.getName() + " continue to train. What would you like to do?" );
    }

    @FXML
    protected void goHome()
    {
        pet.train();
        root.changeMessage("You and " + pet.getName() + " returned home. What would you like to do now?");
        root.transitionDisplay(new LivingRoom(user, root));
        root.transitionMenu(new Home(root, user));
    }

    private void losePet()
    {
        continueTraining.setVisible(false);
        goHome.setVisible(false);

        new Timeline(new KeyFrame(
                Duration.millis(3000),
                ae -> {
                    root.transitionMenu(new Home(root,user));
                    root.transitionDisplay(new LivingRoom(user, root));
                    if(pet.getSpecies().equals("fish"))
                    {
                        root.changeMessage(pet.getName() + " died due to lack of water.");
                    }
                })
        ).play();

        user.removePet(pet);

    }
}
