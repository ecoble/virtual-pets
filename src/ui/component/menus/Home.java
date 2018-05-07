package ui.component.menus;

import commands.FoodType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Json;
import model.User;
import ui.component.Component;
import ui.component.Root;

import java.io.File;
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
        root.changeMessage("You have $" + user.getMoney() + ". \nFood Inventory: \n"
                + "Dog: " + user.getFood(FoodType.DOG) + " "
                + "Cat: " + user.getFood(FoodType.CAT) + " "
                + "Bird: " + user.getFood(FoodType.BIRD) + " "
                + "Fish: " + user.getFood(FoodType.FISH) + " "
                + "Rabbit: " + user.getFood(FoodType.RABBIT) + " ");
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
        String fileName = null;


        if(!root.getIsNew())
        {
            fileName = root.getFileName();
            Stage stage = (Stage) quitButton.getScene().getWindow();
            stage.close();
        }
        else
        {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialFileName(user.getName());
            File file= fileChooser.showSaveDialog(quitButton.getScene().getWindow());

            if(file == null)
            {
                return;
            }

            fileName = file.getName();

            Stage stage = (Stage) quitButton.getScene().getWindow();
            stage.close();
        }

        try
        {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(Json.to(user));
            fileWriter.flush();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
