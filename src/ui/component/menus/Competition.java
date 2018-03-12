package ui.component.menus;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import model.Pet;
import model.User;
import ui.component.Component;
import ui.component.Root;
import ui.component.environments.LivingRoom;

public class Competition extends HBox
{
    private Root root;
    private User user;
    private Pet pet;

    @FXML
    private Button continueCompeting;

    @FXML
    private Button goHome;

    public Competition (Root root, User user, Pet pet)
    {
        this.root = root;
        this.user = user;
        this.pet = pet;
        Component.load("Competition.fxml", this);
    }

    @FXML
    protected void initialize()
    {

        if(pet.getSpecies().equals("fish"))
        {
            root.changeMessage("Your fish, " + pet.getName() + ", is competing, but not for long!");
            losePet();
        }
        else
        {
            root.changeMessage(pet.getName() + " is competing in a " + pet.getSpecies() + " competition!. What would you like to do?");
        }


        pet.hungerStatProperty().addListener((change ->
        {
            if (pet.getHungerStat() <= 0)
            {
                user.removePet(pet);
                root.transitionDisplay(new LivingRoom(user));
                root.transitionMenu(new Home(root, user));
            }
        }));

        pet.thirstStatProperty().addListener((change -> {
            if(pet.getThirstStat() <= 0)
            {
                user.removePet(pet);
                root.transitionDisplay(new LivingRoom(user));
                root.transitionMenu(new Home(root, user));
            }
        }));

        pet.hygieneStatProperty().addListener((changeStat ->
        {
            if (pet.getHygieneStat() == 0)
            {
                root.changeMessage(pet.getName() + " is very dirty! You should give them a bath!");
            }
        }));
    }

    @FXML
    protected void continueCompeting()
    {
        root.changeMessage("You and " + pet.getName() + " continue to compete. What would you like to do?" );
    }

    @FXML
    protected void goHome()
    {
        if(pet.getSkillPoints() > 100)
        {
            root.changeMessage("You and " + pet.getName() + " returned home. You won $250! What would you like to do now?");
            user.addMoney(250);
        }
        else if (pet.getSkillPoints() >= 50 && pet.getSkillPoints() <= 100)
        {
            root.changeMessage("You and " + pet.getName() + " returned home. You won $100! What would you like to do now?");
            user.addMoney(100);
        }
        else
        {
            root.changeMessage("You and " + pet.getName() + " returned home. You didn't win any money :( What would you like to do now?");
        }

        root.transitionDisplay(new LivingRoom(user));
        root.transitionMenu(new Home(root, user));
    }

    private void losePet()
    {
        continueCompeting.setVisible(false);
        goHome.setVisible(false);

        new Timeline(new KeyFrame(
                Duration.millis(3000),
                ae -> {
                    root.transitionMenu(new Home(root,user));
                    root.transitionDisplay(new LivingRoom(user));
                    if(pet.getSpecies().equals("fish"))
                    {
                        root.changeMessage(pet.getName() + " died due to lack of water.");
                    }
                })
        ).play();

        user.removePet(pet);

    }
}
