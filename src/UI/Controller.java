package UI;
import Commands.FoodPurchaseCommand;
import Commands.PetPurchaseCommand;
import Model.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.Optional;

public class Controller {
    private User user;

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
    private Button buyFoodButton;

    @FXML
    protected void buyDog(MouseEvent event)
    {
        PetView view = new PetView(new Dog("dog"), "images/golden-retriever.png", 250, landPetContainer);
        buyPet(view, Dog.price);
    }

    @FXML
    protected void buyCat(MouseEvent event)
    {
        PetView view = new PetView(new Cat("cat"), "images/cat_image.png", 150, landPetContainer);
        buyPet(view, Cat.price);

    }

    @FXML
    protected void buyFish(MouseEvent event)
    {
        PetView view = new PetView(new Fish("fish"),"images/goldfish.png", 75, fishContainer);
        buyPet(view, Fish.price);

    }

    @FXML
    protected void buyBird(MouseEvent event)
    {
        PetView view = new PetView(new Bird("bird"), "images/bird.png", 150, birdContainer);
        buyPet(view, Bird.price);
    }

    @FXML
    protected void buyRabbit(MouseEvent event)
    {
        PetView view = new PetView(new Rabbit("rabbit"),"images/rabbit.png", 50, landPetContainer);
        buyPet(view, Rabbit.price);

    }

    private void buyPet(PetView view, int price) {
        if(!checkPrice(price))
        {
            return;
        }

        userMessage.setText("You bought a " + view.getPet().getSpecies() + "! You need to name your " + view.getPet().getSpecies() + "!");

        Image image = new Image(view.getImageUrl());
        ImageView img = new ImageView();
        img.setPreserveRatio(true);
        img.setFitWidth(view.getImageSize());
        img.setImage(image);

        if (view.getPet().getSpecies().equals("bird")) {
            birdContainer.getChildren().add(img);
        } else if (view.getPet().getSpecies().equals("fish")) {
            fishContainer.getChildren().add(img);
        } else {
            AnchorPane anchor = new AnchorPane(img);
            anchor.setBottomAnchor(img, 0.0);
            landPetContainer.getChildren().add(anchor);
        }


        hidePetBox();
        showHomeBox();

        view.getPet().setName(collectInput("Enter Pet Name", "Name your pet!"));

        PetPurchaseCommand command = new PetPurchaseCommand(view.getPet(), price);
        user.purchase(command);


    }

    @FXML
    protected void showMorePets(MouseEvent event)
    {
        hideHomeBox();
        showPetBox();
        userMessage.setText("You have $" + user.getMoney() + ".");
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
        userMessage.setText("You have $" + user.getMoney() + ".");
    }

    @FXML
    protected void buyFood(MouseEvent event)
    {
        hideStoreBox();
        showHomeBox();

        if(!checkPrice(Pet.foodPrice))
        {
            return;
        }

        FoodPurchaseCommand command = new FoodPurchaseCommand(Pet.foodPrice);
        user.purchase(command);

        userMessage.setText("You bought food! What would you like to do now?");
    }

    @FXML
    protected void interact(MouseEvent event)
    {
        hideInteractBox();
        showHomeBox();
        Button b = (Button) event.getSource();
        userMessage.setText(b.getUserData().toString() + " What would you like to do now?");

    }

    //Welcome sequence
    @FXML
    protected void initialize()
    {
        user = new User(collectInput("Enter your name:", "Welcome to Virtual Pets!"));
        userMessage.setText("Hello " + user.getName() + "! You need to buy your first pet!");
        showPetBox();

        dogButton.setText("Buy Dog: $" + Dog.price);
        catButton.setText("Buy Cat: $" + Cat.price);
        fishButton.setText("Buy Fish: $" + Fish.price);
        birdButton.setText("Buy Bird: $" + Bird.price);
        rabbitButton.setText("Buy Rabbit: $" + Rabbit.price);
        buyFoodButton.setText("Buy Food: $" + Pet.foodPrice);

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

    private void pauseForMessage(String message)
    {
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(1500),
                ae -> userMessage.setText(message))
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

        if(!header.equals("Welcome to Virtual Pets!")) {
            userMessage.setText("What would you like to do now?");
        }
        return name.orElse("");
    }

    private boolean checkPrice(int price)
    {
        if(!user.canAfford(price))
        {
            userMessage.setText("You don't have enough money for that!");
            hidePetBox();
            showHomeBox();
            pauseForMessage("What would you like to do now?");
            return false;
        }

        return true;

    }

}
