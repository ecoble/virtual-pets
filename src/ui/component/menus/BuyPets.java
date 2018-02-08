package ui.component.menus;

import commands.PetPurchaseCommand;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import ui.component.Component;
import ui.component.PetView;
import ui.component.Root;

import java.util.Optional;

/**
 * Created by M5sp on 10/18/17.
 */
public class BuyPets extends VBox
{
    private Root root;

    private User user;

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

    public BuyPets(Root root, User user)
    {
        this.root = root;
        this.user = user;
        Component.load("BuyPets.fxml", this);
    }

    @FXML
    protected void initialize()
    {
        dogButton.setText("Buy Dog: $" + Dog.price);
        catButton.setText("Buy Cat: $" + Cat.price);
        fishButton.setText("Buy Fish: $" + Fish.price);
        birdButton.setText("Buy Bird: $" + Bird.price);
        rabbitButton.setText("Buy Rabbit: $" + Rabbit.price);
    }

    @FXML
    protected void buyDog(MouseEvent event)
    {
        PetView view = new PetView(new Dog(), "images/golden-retriever.png", 250);
        buyPet(view, Dog.price);
    }

    @FXML
    protected void buyCat(MouseEvent event)
    {
        PetView view = new PetView(new Cat(), "images/cat_image.png", 150);
        buyPet(view, Cat.price);

    }

    @FXML
    protected void buyFish(MouseEvent event)
    {
        PetView view = new PetView(new Fish(), "images/goldfish.png", 75);
        buyPet(view, Fish.price);

    }

    @FXML
    protected void buyBird(MouseEvent event)
    {
        PetView view = new PetView(new Bird(), "images/bird.png", 150);
        buyPet(view, Bird.price);
    }

    @FXML
    protected void buyRabbit(MouseEvent event)
    {
        PetView view = new PetView(new Rabbit(), "images/rabbit.png", 50);
        buyPet(view, Rabbit.price);

    }

    @FXML
    protected void cancel(MouseEvent event)
    {
        root.transitionMenu(new Home(root, user));
        root.changeMessage("What would you like to do now?");
    }

    private void buyPet(PetView view, int price) {
        if(!root.checkPrice(price, user))
        {
            return;
        }

        if(!checkSpace(view.getPet()))
        {
            return;
        }

        root.changeMessage("You bought a " + view.getPet().getSpecies() + "! You need to name your " + view.getPet().getSpecies() + "!");

        root.transitionMenu(new Home(root, user));

        String name = collectInput("Enter Pet Name", "Name your pet!");
        while(checkIfSameName(name))
        {
            root.changeMessage("You already have a pet with that name!");
            name = collectInput("Enter Pet Name", "Name your pet!");
        }

        while(checkNameLength(name))
        {
            root.changeMessage("That name is too long!");
            name = collectInput("Enter Pet Name", "Name your pet!");
        }

        view.getPet().setName(name);

        PetPurchaseCommand command = new PetPurchaseCommand(view.getPet(), price);
        user.purchase(command);

        root.changeMessage("What would you like to do now?");
    }

    private String collectInput(String message, String header)
    {
        TextInputDialog input = new TextInputDialog();
        input.getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);
        input.setTitle("Virtual Pets");
        input.setHeaderText(header);
        input.setContentText(message);
        Optional<String> name = input.showAndWait();
        return name.orElse("");
    }

    private boolean checkSpace(Pet pet)
    {
        if(!user.hasSpace(pet))
        {
            root.changeMessage("You don't have enough space for that!");
            root.transitionMenu(new Home(root, user));
            root.pauseForMessage("What would you like to do now?");
            return false;
        }

        return true;
    }

    private boolean checkIfSameName(String name)
    {
        return user.getPets().stream().anyMatch(pet -> pet.getName().equals(name));
    }

    private boolean checkNameLength(String name)
    {
        return name.length() > 30;
    }

}
