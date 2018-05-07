package ui.component.environments;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.Pet;
import model.User;
import ui.component.Component;
import ui.component.PetView;
import ui.component.Root;
import ui.component.menus.Home;

import static model.PetType.BIRD;
import static model.PetType.LAND;

/**
 * Created by M5sp on 11/10/17.
 */
public class WalkEnvironment extends VBox
{
    private Root root;
    private User user;
    private Pet pet;

    @FXML
    private HBox petContainer;

    @FXML
    private HBox birdContainer;

    @FXML
    private Button continueWalk;

    @FXML
    private Button goHome;

    @FXML
    private Text userMessage;


    public WalkEnvironment(Root root, User user, Pet pet)
    {
        this.root = root;
        this.user = user;
        this.pet = pet;
        Component.load("WalkEnvironment.fxml", this);
    }

    @FXML
    protected void initialize()
    {
        switch (pet.getSpecies())
        {
            case "dog":
                petContainer.getChildren().add(new PetView(pet, "images/golden-retriever.png", 250));
                userMessage.setText("You're walking " + pet.getName() + ". What would you like to do?");
                break;
            case "cat":
                petContainer.getChildren().add(new PetView(pet, "images/cat_image.png", 150));
                userMessage.setText("You're walking " + pet.getName() + ", but they're getting upset. What would you like to do?");
                break;
            case "rabbit":
                petContainer.getChildren().add(new PetView(pet, "images/rabbit.png", 50));
                userMessage.setText("You're walking " + pet.getName() + ", but it is not going well. What would you like to do?");
                break;
            case "bird":
                birdContainer.getChildren().add(new PetView(pet, "images/bird.png", 150));
                userMessage.setText("You tried to walk your bird, " + pet.getName() + ", but they're flying away! Oh no!");
                losePet();
                break;
            case "fish":
                petContainer.getChildren().add(new PetView(pet, "images/goldfish.png", 75));
                userMessage.setText("You're walking your fish, " + pet.getName() + ", but not for long.");
                losePet();
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
    protected void continueWalk()
    {
        userMessage.setText("You and " + pet.getName() + " continue to walk. What would you like to do?" );
    }

    @FXML
    protected void goHome()
    {
        root.changeMessage("You and " + pet.getName() + " returned home. What would you like to do now?");
        root.transitionDisplay(new LivingRoom(user, root));
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
                    root.transitionDisplay(new LivingRoom(user, root));

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
