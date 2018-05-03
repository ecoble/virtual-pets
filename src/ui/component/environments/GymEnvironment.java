package ui.component.environments;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
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

/**
 * Created by M5sp on 11/20/17.
 */
public class GymEnvironment extends VBox
{
    private Root root;
    private User user;
    private Pet pet;

    @FXML
    private HBox petContainer;

    @FXML
    private HBox birdContainer;

    @FXML
    private Button continueTraining;

    @FXML
    private Button goHome;

    @FXML
    private Text userMessage;

    public GymEnvironment(Root root, User user, Pet pet)
    {
        this.root = root;
        this.user = user;
        this.pet = pet;
        Component.load("GymEnvironment.fxml", this);
    }

    @FXML
    protected void initialize()
    {
        switch (pet.getSpecies())
        {
            case "dog":
                petContainer.getChildren().add(new PetView(pet, "images/golden-retriever.png", 250));
                userMessage.setText("You're training " + pet.getName() + ". They are learning a lot! What would you like to do?");
                break;
            case "cat":
                petContainer.getChildren().add(new PetView(pet, "images/cat_image.png", 150));
                userMessage.setText("You're training " + pet.getName() + ", but they don't seem interested. What would you like to do?");
                break;
            case "rabbit":
                petContainer.getChildren().add(new PetView(pet, "images/rabbit.png", 50));
                userMessage.setText("You're training " + pet.getName() + ". What would you like to do?");
                break;
            case "bird":
                birdContainer.getChildren().add(new PetView(pet, "images/bird.png", 150));
                userMessage.setText("You're training your bird, " + pet.getName() + ". They are learning a lot! What would you like to do?");
                break;
            case "fish":
                petContainer.getChildren().add(new PetView(pet, "images/goldfish.png", 75));
                userMessage.setText("You're training your fish, " + pet.getName() + ", but not for long!");
                break;
        }

        pet.hungerStatProperty().addListener((change ->
        {
            if (pet.getHungerStat() <= 0)
            {
                user.removePet(pet);
                root.transitionDisplay(new LivingRoom(user));
                root.transitionMenu(new Home(root, user));
            }
        }));

        pet.thirstStatProperty().addListener((change -> {
            if(pet.getThirstStat() <= 0)
            {
                user.removePet(pet);
                root.transitionDisplay(new LivingRoom(user));
                root.transitionMenu(new Home(root, user));
            }
        }));

        pet.hygieneStatProperty().addListener((changeStat ->
        {
            if (pet.getHygieneStat() == 0)
            {
                userMessage.setText(pet.getName() + " is very dirty! You should give them a bath!");
            }
        }));
    }

    @FXML
    protected void continueTraining()
    {
        userMessage.setText("You and " + pet.getName() + " continue to train. What would you like to do?" );
    }

    @FXML
    protected void goHome()
    {
        pet.train();
        root.changeMessage("You and " + pet.getName() + " returned home. What would you like to do now?");
        root.transitionDisplay(new LivingRoom(user));
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
                    root.transitionDisplay(new LivingRoom(user));
                    if(pet.getSpecies().equals("fish"))
                    {
                        root.changeMessage(pet.getName() + " died due to lack of water.");
                    }
                })
        ).play();

        user.removePet(pet);

    }
}

