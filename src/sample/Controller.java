package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.awt.event.ActionEvent;

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
    private Button dogButton;

    @FXML
    private Button catButton;

    @FXML
    private Button fishButton;

    @FXML
    private Button birdButton;

    @FXML
    private Button rabbitButton;

    @FXML
    private Button morePetsButton;

    @FXML
    private Button interactButton;

    @FXML
    private Button storeButton;

    @FXML
    private Button foodButton;

    @FXML
    private Button drinkButton;

    @FXML
    private Button walkButton;

    @FXML
    private Button washButton;

    @FXML
    private Button trainButton;

    @FXML
    private Button buyFoodButton;

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
    protected void morePets(MouseEvent event)
    {
        hideHomeButtons();
        showMorePetsButtons();
    }

    @FXML
    protected void interact(MouseEvent event)
    {
        hideHomeButtons();
        showInteractButtons();
    }

    @FXML
    protected void store(MouseEvent event)
    {
        hideHomeButtons();
        showStoreButtons();
    }

    @FXML
    protected void feed(MouseEvent event)
    {
        hideInteractButtons();
        showHomeButtons();
        userMessage.setText("You fed your pet!");
        returnToHomeMessage();
    }

    @FXML
    protected void drink(MouseEvent event)
    {
        hideInteractButtons();
        showHomeButtons();
        userMessage.setText("You gave water to your pet!");
        returnToHomeMessage();
    }

    @FXML
    protected void walk(MouseEvent event)
    {
        hideInteractButtons();
        showHomeButtons();
        userMessage.setText("You walked your pet!");
        returnToHomeMessage();
    }

    @FXML
    protected void wash(MouseEvent event)
    {
        hideInteractButtons();
        showHomeButtons();
        userMessage.setText("You washed your pet!");
        returnToHomeMessage();
    }

    @FXML
    protected void train(MouseEvent event)
    {
        hideInteractButtons();
        showHomeButtons();
        userMessage.setText("You trained your pet!");
        returnToHomeMessage();
    }

    @FXML
    protected void buyFood(MouseEvent event)
    {
        hideStoreButtons();
        showHomeButtons();
        userMessage.setText("You bought food!");
        returnToHomeMessage();
    }


    //Welcome sequence
    @FXML
    protected void initialize()
    {
        showMorePetsButtons();
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

        hideMorePetsButtons();
        showHomeButtons();

    }

    private void showHomeButtons() {
        morePetsButton.setVisible(true);
        morePetsButton.setManaged(true);
        interactButton.setVisible(true);
        interactButton.setManaged(true);
        storeButton.setVisible(true);
        storeButton.setManaged(true);
    }

    private void hideHomeButtons() {
        morePetsButton.setVisible(false);
        morePetsButton.setManaged(false);
        interactButton.setVisible(false);
        interactButton.setManaged(false);
        storeButton.setVisible(false);
        storeButton.setManaged(false);

    }

    private void showMorePetsButtons() {
        dogButton.setVisible(true);
        dogButton.setManaged(true);
        catButton.setVisible(true);
        catButton.setManaged(true);
        fishButton.setVisible(true);
        fishButton.setManaged(true);
        birdButton.setVisible(true);
        birdButton.setManaged(true);
        rabbitButton.setVisible(true);
        rabbitButton.setManaged(true);

    }

    private void hideMorePetsButtons() {
        dogButton.setVisible(false);
        dogButton.setManaged(false);
        catButton.setVisible(false);
        catButton.setManaged(false);
        fishButton.setVisible(false);
        fishButton.setManaged(false);
        birdButton.setVisible(false);
        birdButton.setManaged(false);
        rabbitButton.setVisible(false);
        rabbitButton.setManaged(false);
    }

    private void showInteractButtons() {
        foodButton.setVisible(true);
        foodButton.setManaged(true);
        drinkButton.setVisible(true);
        drinkButton.setManaged(true);
        walkButton.setVisible(true);
        walkButton.setManaged(true);
        washButton.setVisible(true);
        washButton.setManaged(true);
        trainButton.setVisible(true);
        trainButton.setManaged(true);
    }

    private void hideInteractButtons() {
        foodButton.setVisible(false);
        foodButton.setManaged(false);
        drinkButton.setVisible(false);
        drinkButton.setManaged(false);
        walkButton.setVisible(false);
        walkButton.setManaged(false);
        washButton.setVisible(false);
        washButton.setManaged(false);
        trainButton.setVisible(false);
        trainButton.setManaged(false);
    }

    private void showStoreButtons()
    {
        buyFoodButton.setVisible(true);
        buyFoodButton.setManaged(true);
    }

    private void hideStoreButtons()
    {
        buyFoodButton.setVisible(false);
        buyFoodButton.setManaged(false);
    }

    private void returnToHomeMessage()
    {
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(1500),
                ae -> userMessage.setText("What would you like to do now?"))
        );

        timeline.play();
    }

}
