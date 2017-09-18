package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Optional;

public class Controller {
    @FXML
    private HBox landPetContainer;

    @FXML
    private HBox birdContainer;

    @FXML
    private StackPane fishContainer;

    @FXML
    private Text userMessage;

    @FXML
    private HBox homeBox;

    @FXML
    private HBox petBox;

    @FXML
    private HBox interactBox;

    @FXML
    private HBox storeBox;

    @FXML
    protected void buyDog(MouseEvent event) {
        addAnimal("dog", "images/golden-retriever.png", 250);
    }

    @FXML
    protected void buyCat(MouseEvent event) {
        addAnimal("cat", "images/cat_image.png", 150);
    }

    @FXML
    protected void buyFish(MouseEvent event) {
        addAnimal("fish", "images/goldfish.png", 75);
    }

    @FXML
    protected void buyBird(MouseEvent event) {
        addAnimal("bird", "images/bird.png", 150);
    }

    @FXML
    protected void buyRabbit(MouseEvent event) {
        addAnimal("rabbit", "images/rabbit.png", 50);
    }

    @FXML
    protected void showMorePets(MouseEvent event)
    {
        hideHomeBox();
        showPetBox();
    }

    @FXML
    protected void showInteract(MouseEvent event)
    {
        hideHomeBox();
        showInteractBox();
    }

    @FXML
    protected void showStore(MouseEvent event)
    {
        hideHomeBox();
        showStoreBox();
    }

    @FXML
    protected void buyFood(MouseEvent event)
    {
        hideStoreBox();
        showHomeBox();
        userMessage.setText("You bought food!");
        returnToHomeMessage();
    }

    @FXML
    protected void interact(MouseEvent event)
    {
        hideInteractBox();
        showHomeBox();
        Button b = (Button) event.getSource();
        userMessage.setText(b.getUserData().toString());
        returnToHomeMessage();

    }


    //Welcome sequence
    @FXML
    protected void initialize()
    {
        userMessage.setText("You need to buy your first pet!");
        showPetBox();
        User user1 = new User(collectInput("Enter your name:", "Welcome to Virtual Pets!"));
    }


    private void addAnimal(String petType, String url, int size) {
        userMessage.setText("You bought a " + petType + "!");
        returnToHomeMessage();

        Image image = new Image(url);
        ImageView img = new ImageView();
        img.setPreserveRatio(true);
        img.setFitWidth(size);
        img.setImage(image);

        if (petType.equals("bird")) {
            birdContainer.getChildren().add(img);
        } else if (petType.equals("fish")) {
            fishContainer.getChildren().add(img);
        } else {
            AnchorPane anchor = new AnchorPane(img);
            anchor.setBottomAnchor(img, 0.0);
            landPetContainer.getChildren().add(anchor);
        }

        hidePetBox();
        showHomeBox();

    }

    private void showHomeBox()
    {
        homeBox.setVisible(true);
        homeBox.setManaged(true);
    }

    private void hideHomeBox()
    {
        homeBox.setVisible(false);
        homeBox.setManaged(false);
    }

    private void showPetBox()
    {
        petBox.setVisible(true);
        petBox.setManaged(true);
    }

    private void hidePetBox()
    {
        petBox.setVisible(false);
        petBox.setManaged(false);
    }

    private void showInteractBox()
    {
        interactBox.setVisible(true);
        interactBox.setManaged(true);
    }

    private void hideInteractBox()
    {
        interactBox.setVisible(false);
        interactBox.setManaged(false);
    }

    private void showStoreBox()
    {
        storeBox.setVisible(true);
        storeBox.setManaged(true);
    }

    private void hideStoreBox()
    {
        storeBox.setVisible(false);
        storeBox.setManaged(false);
    }

    private void returnToHomeMessage()
    {
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(1500),
                ae -> userMessage.setText("What would you like to do now?"))
        );

        timeline.play();
    }

    private String collectInput(String message, String header)
    {
        TextInputDialog input = new TextInputDialog();
        input.setTitle("Virtual Pets");
        input.setHeaderText(header);
        input.setContentText(message);
        Optional<String> name = input.showAndWait();


        return name.toString();

    }

}
