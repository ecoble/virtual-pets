package ui.component.menus;

import commands.FoodPurchaseCommand;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Pet;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import model.User;
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
    private Button buyFoodButton;

    public Shop(Root root, User user)
    {
        this.root = root;
        this.user = user;
        Component.load("Shop.fxml", this);
    }

    @FXML
    protected void initialize()
    {
        buyFoodButton.setText("Buy Food: $" + Pet.foodPrice);
    }

    @FXML
    protected void cancel(MouseEvent event)
    {
        root.transitionMenu(new Home(root, user));
        root.changeMessage("What would you like to do now?");
    }

    @FXML
    protected void buyFood(MouseEvent event)
    {
        root.transitionMenu(new Home(root, user));

        if(!root.checkPrice(Pet.foodPrice, user))
        {
            return;
        }

        FoodPurchaseCommand command = new FoodPurchaseCommand(Pet.foodPrice);
        user.purchase(command);

        root.changeMessage("You bought food! What would you like to do now?");
    }
}
