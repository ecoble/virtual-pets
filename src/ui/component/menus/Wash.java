package ui.component.menus;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import model.Pet;
import model.User;
import ui.component.Component;
import ui.component.Root;
import ui.component.environments.LivingRoom;
import ui.component.menus.Home;

/**
 * Created by M5sp on 11/13/17.
 */
public class Wash extends HBox
{
    private Root root;
    private User user;
    private Pet pet;

    @FXML
    private Button continueWash;

    @FXML
    private Button finishWash;

    public Wash (Root root, User user, Pet pet)
    {
        this.root = root;
        this.user = user;
        this.pet = pet;
        Component.load("Wash.fxml", this);
    }

    @FXML
    protected void initialize()
    {
        switch(pet.getSpecies())
        {
            case "dog":
                root.changeMessage("You're washing " + pet.getName() + ". What would you like to do?");
                break;
            case "cat":
                root.changeMessage("You're washing your cat, " + pet.getName() + ", but they're scratching you. What would you like to do?");
                break;
            case "rabbit":
                root.changeMessage("You're washing " + pet.getName() + ", but it is not going well. What would you like to do?");
                break;
            case "fish":
                root.changeMessage("You're washing your fish, " + pet.getName() + ". It is ineffective.");
                break;
            case "bird":
                root.changeMessage("You're washing your bird, " + pet.getName() + ", but they're getting upset. What would you like to do?");
                break;
        }

        pet.hungerStatProperty().addListener((change ->
        {
            if (pet.getHungerStat() <= 0)
            {
                user.removePet(pet);
                //root.transitionDisplay(new LivingRoom(user, root));
                root.transitionMenu(new Home(root, user));
            }
        }));

        pet.thirstStatProperty().addListener((change -> {
            if(pet.getThirstStat() <= 0)
            {
                user.removePet(pet);
                //root.transitionDisplay(new LivingRoom(user, root));
                root.transitionMenu(new Home(root, user));
            }
        }));
    }


    @FXML
    protected void continueWash()
    {
        root.changeMessage("You continue to wash " + pet.getName() + ". What would you like to do?" );
    }

    @FXML
    protected void finishWash()
    {
        pet.wash();

        if(pet.getSpecies().equals("cat"))
        {
            if(user.getMoney() >= 50)
            {
                root.changeMessage("You finished washing " + pet.getName() + ", but you had to go to the doctor for your scratches. The bill was $50.");
                user.withdrawMoney(50);
            }
            else
            {
                root.changeMessage("You finished washing " + pet.getName() + ", and didn't have enough money to see the doctor for you scratches.\nYour scratches cause you great suffering.");
            }
        }
        else
        {
            root.changeMessage("You finished washing " + pet.getName() + ". What would you like to do now?");
        }
        //root.transitionDisplay(new LivingRoom(user, root));
        root.transitionMenu(new Home(root, user));
    }
}
