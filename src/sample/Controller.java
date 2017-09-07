package sample;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.awt.event.ActionEvent;

public class Controller
{
    @FXML
    private HBox landPetContainer;

    @FXML
    private HBox birdContainer;

    @FXML
    private StackPane fishContainer;

    @FXML
    private Text userMessage;

    @FXML
    protected void buyDog(MouseEvent event)
    {
        addAnimal("dog","images/golden-retriever.png", 250);
    }

    @FXML
    protected void buyCat(MouseEvent event)
    {
        addAnimal("cat", "images/cat_image.png", 150);
    }

    @FXML
    protected void buyFish(MouseEvent event)
    {
        addAnimal("fish", "images/goldfish.png", 75);
    }

    @FXML
    protected void buyBird(MouseEvent event)
    {
        addAnimal("bird", "images/bird.png", 150);
    }

    @FXML
    protected void buyRabbit(MouseEvent event)
    {
        addAnimal("rabbit", "images/rabbit.png", 50);
    }

    private void addAnimal(String petType, String url, int size)
    {
        userMessage.setText("You bought a " + petType + "!");

        Image image = new Image(url);
        ImageView img = new ImageView();
        img.setPreserveRatio(true);
        img.setFitWidth(size);
        img.setImage(image);

        if(petType.equals("bird"))
        {
            birdContainer.getChildren().add(img);
        }
        else if(petType.equals("fish"))
        {
            fishContainer.getChildren().add(img);
        }
        else
        {
            AnchorPane anchor = new AnchorPane(img);
            anchor.setBottomAnchor(img, 0.0);
            landPetContainer.getChildren().add(anchor);
        }
    }



}
