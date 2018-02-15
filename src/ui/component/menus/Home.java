package ui.component.menus;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Json;
import model.User;
import ui.component.Component;
import ui.component.Root;

import java.io.FileWriter;

/**
 * Created by M5sp on 10/18/17.
 */
public class Home extends VBox
{
    private Root root;
    private User user;

    @FXML
    private Button quitButton;


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

    @FXML
    protected void quit()
    {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();

        try
        {
            FileWriter fileWriter = new FileWriter("./save.json");
            fileWriter.write(Json.to(user));
            fileWriter.flush();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


    }

}
