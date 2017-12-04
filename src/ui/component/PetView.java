package ui.component;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import model.Pet;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by M5sp on 9/27/17.
 */
public class PetView extends StackPane
{
    private Pet pet;
    private String imageUrl;
    private int imageSize;

    @FXML
    private ImageView petView;

    @FXML
    private ImageView foodView;

    @FXML
    private ImageView waterView;

    public PetView(Pet pet, String imageUrl, int imageSize)
    {
        this.pet = pet;
        this.imageUrl = imageUrl;
        this.imageSize = imageSize;

        Component.load("PetView.fxml", this);
    }

    @FXML
    protected void initialize()
    {
        petView.setFitWidth(imageSize);
        petView.setImage(new Image(imageUrl));

        pet.onFeed(() -> {
            foodView.setImage(new Image("images/food.png"));

            Timeline timeline = new Timeline(new KeyFrame(
                    Duration.millis(5000),
                    ae -> foodView.setImage(null))
            );

            timeline.play();

        });

        pet.onGiveWater(() -> {
            waterView.setImage(new Image("images/water.png"));

            Timeline timeline = new Timeline(new KeyFrame(
                    Duration.millis(5000),
                    ae -> waterView.setImage(null))
            );

            timeline.play();

        });
    }

    public Pet getPet()
    {
        return pet;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public int getImageSize()
    {
        return imageSize;
    }
}
