package ui.component.environments;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import model.Pet;
import ui.component.Component;
import ui.component.PetView;
import ui.component.Root;

import static model.PetType.BIRD;

/**
 * Created by M5sp on 11/13/17.
 */
public class WashEnvironment extends StackPane
{
    private Root root;

    @FXML
    private HBox petContainer;

    public WashEnvironment(Root root)
    {
        this.root = root;
        Component.load("WashEnvironment.fxml", this);
    }

    @FXML
    protected void initialize()
    {
        for(Pet pet : root.user.getPets())
        {
            if(pet.getName().equals(root.currPetName))
            {
                switch (pet.getSpecies())
                {
                    case "dog":
                        addPet(new PetView(pet, "images/golden-retriever.png", 250));
                        break;
                    case "cat":
                        addPet(new PetView(pet, "images/cat_image.png", 150));
                        break;
                    case "rabbit":
                        addPet(new PetView(pet, "images/rabbit.png", 50));
                        break;
                    case "bird":
                        addPet(new PetView(pet, "images/bird.png", 150));
                        break;
                    case "fish":
                        addPet(new PetView(pet, "images/goldfish.png", 75));
                        break;
                }

            }
        }
    }

    public void addPet(PetView view)
    {
        AnchorPane anchor = new AnchorPane(view);
        anchor.setBottomAnchor(view, 0.0);
        petContainer.getChildren().add(anchor);
    }
}
