package ui.component.menus;

import commands.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import ui.component.Component;
import ui.component.Root;
import ui.component.menus.Home;

/**
 * Created by M5sp on 10/18/17.
 */
public class Shop extends VBox
{
    private Root root;
    private User user;

    @FXML
    private Button dogFoodButton;

    @FXML
    private Button catFoodButton;

    @FXML
    private Button birdFoodButton;

    @FXML
    private Button fishFoodButton;

    @FXML
    private Button rabbitFoodButton;

    public Shop(Root root, User user)
    {
        this.root = root;
        this.user = user;
        Component.load("Shop.fxml", this);
    }

    @FXML
    protected void initialize()
    {
        dogFoodButton.setText("Dog Food: $" + Dog.foodPrice);
        catFoodButton.setText("Cat Food: $" + Cat.foodPrice);
        birdFoodButton.setText("Bird Food: $" + Bird.foodPrice);
        fishFoodButton.setText("Fish Food: $" + Fish.foodPrice);
        rabbitFoodButton.setText("Rabbit Food: $" + Rabbit.foodPrice);
    }

    @FXML
    protected void cancel(MouseEvent event)
    {
        root.transitionMenu(new Home(root, user));
        root.changeMessage("What would you like to do now?");
    }

    @FXML
    protected void buyDogFood()
    {
        root.transitionMenu(new Home(root, user));

        if(!root.checkPrice(Dog.foodPrice, user))
        {
            return;
        }

        FoodPurchaseCommand command = new DogFoodPurchaseCommand();
        user.purchase(command);


        root.changeMessage("You bought dog food! What would you like to do now?");
    }

    @FXML
    protected void buyCatFood()
    {
        root.transitionMenu(new Home(root, user));

        if(!root.checkPrice(Cat.foodPrice, user))
        {
            return;
        }

        FoodPurchaseCommand command = new CatFoodPurchaseCommand();
        user.purchase(command);


        root.changeMessage("You bought cat food! What would you like to do now?");
    }

    @FXML
    protected void buyBirdFood()
    {
        root.transitionMenu(new Home(root, user));

        if(!root.checkPrice(Bird.foodPrice, user))
        {
            return;
        }

        FoodPurchaseCommand command = new BirdFoodPurchaseCommand();
        user.purchase(command);


        root.changeMessage("You bought bird food! What would you like to do now?");
    }

    @FXML
    protected void buyFishFood()
    {
        root.transitionMenu(new Home(root, user));

        if(!root.checkPrice(Fish.foodPrice, user))
        {
            return;
        }

        FoodPurchaseCommand command = new FishFoodPurchaseCommand();
        user.purchase(command);


        root.changeMessage("You bought fish food! What would you like to do now?");
    }

    @FXML
    protected void buyRabbitFood()
    {
        root.transitionMenu(new Home(root, user));

        if(!root.checkPrice(Rabbit.foodPrice, user))
        {
            return;
        }

        FoodPurchaseCommand command = new RabbitFoodPurchaseCommand();
        user.purchase(command);


        root.changeMessage("You bought rabbit food! What would you like to do now?");
    }
}
