package ui.component.menus;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import model.Pet;
import model.User;
import ui.component.Component;
import ui.component.Root;
import ui.component.environments.CompetitionEnvironment;
import ui.component.environments.LivingRoom;

public class Competition extends HBox
{
    private Root root;
    private User user;
    private Pet pet;
    private CompetitionEnvironment ce;
    private int count;
    private boolean isFrisbeeMoving;

    @FXML
    private Button start;

    @FXML
    private Button goHome;

    @FXML
    private Button throwFrisbee;

    public Competition (Root root, User user, Pet pet, CompetitionEnvironment ce)
    {
        this.root = root;
        this.user = user;
        this.pet = pet;
        this.ce = ce;
        isFrisbeeMoving = false;
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
            root.changeMessage("Click on " + pet.getName() + " to jump and catch the frisbee. Don't click too early or too late! You have 10 tries. ");
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

        ce.isFrisbeeMoving().addListener((change -> {
            if(ce.getIsFrisbeeMoving())
            {
                throwFrisbee.setDisable(true);
            }
            else
            {
                throwFrisbee.setDisable(false);
            }
        }));
    }

    @FXML
    protected void goHome()
    {
        if(ce.getNumCaught() > 7)
        {
            root.changeMessage("You and " + pet.getName() + " returned home. You won $250! What would you like to do now?");
            user.addMoney(250);
        }
        else if (ce.getNumCaught() >= 4 && ce.getNumCaught() <= 7)
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

    @FXML
    protected void start()
    {
        ce.throwFrisbee();
        count++;

        start.setVisible(false);
        start.setManaged(false);

        throwFrisbee.setVisible(true);
        throwFrisbee.setManaged(true);
    }

    @FXML
    protected void throwFrisbee()
    {

        ce.throwFrisbee();
        count++;

        if(count == 10)
        {
            throwFrisbee.setVisible(false);
            throwFrisbee.setManaged(false);
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2500),
                    ae -> root.changeMessage("The competition is over! " + pet.getName() + " caught " + ce.getNumCaught() + " frisbees!")));

            timeline.play();
        }



    }

}
