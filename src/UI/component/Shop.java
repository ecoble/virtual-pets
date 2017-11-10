package ui.component;

import commands.FoodPurchaseCommand;
import javafx.scene.layout.HBox;
import model.Pet;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import ui.Compositor;

/**
 * Created by M5sp on 10/18/17.
 */
public class Shop extends HBox
{
    private Root root;

    @FXML
    private Button buyFoodButton;

    public Shop(Root root)
    {
        this.root = root;
        Component.load("Shop.fxml", this);
    }

    @FXML
    protected void initialize()
    {
        buyFoodButton.setText("Buy Food: $" + Pet.foodPrice);
    }

    @FXML
    protected void buyFood(MouseEvent event)
    {
        root.transitionMenu(new Home(root));

        if(!root.checkPrice(Pet.foodPrice))
        {
            return;
        }

        FoodPurchaseCommand command = new FoodPurchaseCommand(Pet.foodPrice);
        root.user.purchase(command);

        root.changeMessage("You bought food! What would you like to do now?");
    }
}
