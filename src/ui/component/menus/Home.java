package ui.component.menus;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import model.User;
import ui.component.Component;
import ui.component.Root;

/**
 * Created by M5sp on 10/18/17.
 */
public class Home extends HBox
{
    private Root root;
    private User user;


    public Home(Root root, User user)
    {
        this.root = root;
        this.user = user;
        Component.load("Home.fxml", this);
    }


    @FXML
    protected void buyMorePets(MouseEvent event)
    {
        root.transitionMenu(new BuyPets(root, user));
        root.changeMessage("You have $" + user.getMoney() + ".");
    }

    @FXML
    protected void showStore(MouseEvent event)
    {
        root.transitionMenu(new Shop(root, user));
        root.changeMessage("You have $" + user.getMoney() + ". \n Food Count: " + user.getFood());
    }

    @FXML
    protected void showPetNames(MouseEvent event)
    {
        root.transitionMenu(new PetNames(root, user));
        root.changeMessage("Which pet would you like to interact with?");
    }

}
