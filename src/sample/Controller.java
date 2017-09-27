package sample;

import Commands.FoodPurchaseCommand;
import Commands.PetPurchaseCommand;
import Commands.PurchaseCommand;
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
        buyPet("dog", "images/golden-retriever.png",  250);
        Dog dog = new Dog(collectInput("Enter Pet Name", "Name your pet!"));

        PetPurchaseCommand command = new PetPurchaseCommand(dog, Dog.price);
        user.purchase(command);
        

    }

    @FXML
    protected void buyCat(MouseEvent event)
    {
        buyPet("cat", "images/cat_image.png", 150);
        Cat cat = new Cat(collectInput("Enter Pet Name", "Name your pet!"));

        PetPurchaseCommand command = new PetPurchaseCommand(cat, Cat.price);
        user.purchase(command);

    }

    @FXML
    protected void buyFish(MouseEvent event)
    {
        buyPet("fish", "images/goldfish.png", 75);
        Fish fish = new Fish(collectInput("Enter Pet Name", "Name your pet!"));

        PetPurchaseCommand command = new PetPurchaseCommand(fish, Fish.price);
        user.purchase(command);

    }

    @FXML
    protected void buyBird(MouseEvent event)
    {
        buyPet("bird", "images/bird.png", 150);
        Bird bird = new Bird(collectInput("Enter Pet Name", "Name your pet!"));

        PetPurchaseCommand command = new PetPurchaseCommand(bird, Bird.price);
        user.purchase(command);
    }

    @FXML
    protected void buyRabbit(MouseEvent event) {

        buyPet("rabbit", "images/rabbit.png", 50);
        Rabbit rabbit = new Rabbit(collectInput("Enter Pet Name", "Name your pet!"));

        PetPurchaseCommand command = new PetPurchaseCommand(rabbit, Rabbit.price);
        user.purchase(command);

    }

    private void buyPet(String petType, String url, int size) {
        pauseForMessage("You bought a " + petType + "! You need to name your " + petType + "!");

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
        userMessage.setText(b.getUserData().toString() + "What would you like to do now?");

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
            pauseForMessage("What would you like to do now?");
        }
        return name.orElse("");


    }

}
