package ui.component.menus;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import ui.component.Component;
import ui.component.Root;

/**
 * Created by M5sp on 10/18/17.
 */
public class Home extends HBox
{
    private Root root;

    public Home(Root root)
    {
        this.root = root;
        Component.load("Home.fxml", this);
    }


    @FXML
    protected void buyMorePets(MouseEvent event)
    {
        root.transitionMenu(new BuyPets(root));
        root.changeMessage("You have $" + root.user.getMoney() + ".");
    }

    @FXML
    protected void showStore(MouseEvent event)
    {
        root.transitionMenu(new Shop(root));
        root.changeMessage("You have $" + root.user.getMoney() + ".");
    }

    @FXML
    protected void showPetNames(MouseEvent event)
    {
        root.transitionMenu(new PetNames(root));
        root.changeMessage("Which pet would you like to interact with?");
    }

}
