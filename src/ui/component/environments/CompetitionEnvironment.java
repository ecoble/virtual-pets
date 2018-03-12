package ui.component.environments;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import model.Pet;
import ui.component.Component;
import ui.component.PetView;
import ui.component.Root;

public class CompetitionEnvironment extends StackPane
{
    private Root root;
    private Pet pet;

    @FXML
    private HBox petContainer;

    public CompetitionEnvironment(Root root, Pet pet)
    {
        this.root = root;
        this.pet = pet;
        Component.load("CompetitionEnvironment.fxml", this);
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
                petContainer.getChildren().add(new PetView(pet, "images/bird.png", 150));
                break;
            case "fish":
                petContainer.getChildren().add(new PetView(pet, "images/goldfish.png", 75));
                break;
        }
    }
}
