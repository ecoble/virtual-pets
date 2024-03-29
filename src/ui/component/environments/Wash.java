package ui.component.environments;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Pet;
import model.User;
import ui.component.Component;
import ui.component.PetView;
import ui.component.Root;

import static model.PetType.BIRD;

/**
 * Created by M5sp on 11/13/17.
 */
public class Wash extends VBox
{
    private Root root;
    private User user;
    private Pet pet;

    @FXML
    private HBox petContainer;

    @FXML
    private Button continueWash;

    @FXML
    private Button finishWash;

    @FXML
    private Text userMessage;

    public Wash(Root root, User user, Pet pet)
    {
        this.root = root;
        this.user = user;
        this.pet = pet;
        Component.load("Wash.fxml", this);
    }

    @FXML
    protected void initialize()
    {
         switch (pet.getSpecies())
         {
             case "dog":
                 petContainer.getChildren().add(new PetView(pet, "images/golden-retriever.png", 250));
                 userMessage.setText("You're washing " + pet.getName() + ". What would you like to do?");
                 break;
             case "cat":
                 petContainer.getChildren().add(new PetView(pet, "images/cat_image.png", 150));
                 userMessage.setText("You're washing your cat, " + pet.getName() + ", but they're scratching you. What would you like to do?");
                 break;
             case "rabbit":
                 petContainer.getChildren().add(new PetView(pet, "images/rabbit.png", 50));
                 userMessage.setText("You're washing " + pet.getName() + ", but it is not going well. What would you like to do?");
                 break;
             case "bird":
                 petContainer.getChildren().add(new PetView(pet, "images/bird.png", 150));
                 userMessage.setText("You're washing your bird, " + pet.getName() + ", but they're getting upset. What would you like to do?");
                 break;
             case "fish":
                 petContainer.getChildren().add(new PetView(pet, "images/goldfish.png", 75));
                 userMessage.setText("You're washing your fish, " + pet.getName() + ". It is ineffective.");
                 break;
         }

        pet.hungerStatProperty().addListener((change ->
        {
            if (pet.getHungerStat() <= 0)
            {
                user.removePet(pet);
                root.transition(new LivingRoom(user, root, pet.getName() + " died from hunger!"));
            }
        }));

        pet.thirstStatProperty().addListener((change -> {
            if(pet.getThirstStat() <= 0)
            {
                user.removePet(pet);
                root.transition(new LivingRoom(user, root, pet.getName() + " died from thirst!"));
            }
        }));
    }

    @FXML
    protected void continueWash()
    {
        userMessage.setText("You continue to wash " + pet.getName() + ". What would you like to do?" );
    }

    @FXML
    protected void finishWash()
    {
        pet.wash();

        if(pet.getSpecies().equals("cat"))
        {
            if(user.getMoney() >= 50)
            {
                root.transition(new LivingRoom(user, root, "You finished washing " + pet.getName() + ", but you had to go to the doctor for your scratches. The bill was $50."));
                user.withdrawMoney(50);
            }
            else
            {
                root.transition(new LivingRoom(user, root, "You finished washing " + pet.getName() + ", and didn't have enough money to see the doctor for you scratches.\nYour scratches cause you great suffering."));
            }
        }
        else
        {
            root.transition(new LivingRoom(user, root, "You finished washing " + pet.getName() + ". What would you like to do now?"));
        }
    }

}
