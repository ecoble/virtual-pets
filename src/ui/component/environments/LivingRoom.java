package ui.component.environments;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import model.*;
import ui.component.Component;
import ui.component.PetView;

/**
 * Created by M5sp on 10/19/17.
 */
public class LivingRoom extends StackPane
{
    private User user;

    @FXML
    private HBox landPetContainer;

    @FXML
    private HBox birdContainer;

    @FXML
    private StackPane fishContainer;

    public LivingRoom(User user)
    {
        this.user = user;
        this.user.getPets().addListener((ListChangeListener)(change -> {
            while (change.next())
            {
                change.getAddedSubList().forEach(pet -> {
                    if(pet instanceof model.Dog)
                    {
                        addPet(new PetView(new Dog(), "images/golden-retriever.png", 250));
                    }
                    else if(pet instanceof model.Cat)
                    {
                        addPet(new PetView(new Cat(), "images/cat_image.png", 150));
                    }
                    else if(pet instanceof model.Bird)
                    {
                        addPet(new PetView(new Bird(), "images/bird.png", 150));
                    }
                    else if(pet instanceof model.Fish)
                    {
                        addPet(new PetView(new Fish(), "images/goldfish.png", 75));
                    }
                    else if(pet instanceof model.Rabbit)
                    {
                        addPet( new PetView(new Rabbit(), "images/rabbit.png", 50));
                    }
                });
            }
        }));

        Component.load("LivingRoom.fxml", this);
    }

    @FXML
    protected void initialize()
    {
        for(Pet pet : user.getPets())
        {
            if(pet instanceof model.Dog)
            {
                addPet(new PetView(new Dog(), "images/golden-retriever.png", 250));
            }
            else if(pet instanceof model.Cat)
            {
                addPet(new PetView(new Cat(), "images/cat_image.png", 150));
            }
            else if(pet instanceof model.Bird)
            {
                addPet(new PetView(new Bird(), "images/bird.png", 150));
            }
            else if(pet instanceof model.Fish)
            {
                addPet(new PetView(new Fish(), "images/goldfish.png", 75));
            }
            else if(pet instanceof model.Rabbit)
            {
                addPet( new PetView(new Rabbit(), "images/rabbit.png", 50));
            }
        }
    }

    public void addPet(PetView view)
    {
        switch(view.getPet().getType()) {
            case LAND:
                AnchorPane anchor = new AnchorPane(view);
                anchor.setBottomAnchor(view, 0.0);
                landPetContainer.getChildren().add(anchor);
                break;
            case BIRD:
                birdContainer.getChildren().add(view);
                break;
            case FISH:
                fishContainer.getChildren().add(view);
                break;
        }
    }
}