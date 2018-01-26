package ui.component.environments;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import model.Pet;
import model.User;
import ui.component.Component;
import ui.component.PetView;
import ui.component.Root;

import static model.PetType.BIRD;
import static model.PetType.LAND;

/**
 * Created by M5sp on 11/10/17.
 */
public class WalkEnvironment extends StackPane
{
    private Root root;
    private Pet pet;

    @FXML
    private HBox petContainer;

    @FXML
    private HBox birdContainer;

    public WalkEnvironment(Root root, Pet pet)
    {
        this.root = root;
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
                break;
            case "cat":
                petContainer.getChildren().add(new PetView(pet, "images/cat_image.png", 150));
                break;
            case "rabbit":
                petContainer.getChildren().add(new PetView(pet, "images/rabbit.png", 50));
                break;
            case "bird":
                birdContainer.getChildren().add(new PetView(pet, "images/bird.png", 150));
                break;
            case "fish":
                petContainer.getChildren().add(new PetView(pet, "images/goldfish.png", 75));
                break;
        }
    }
}
