package ui.component;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import ui.Compositor;
import ui.component.environments.LivingRoom;
import ui.component.menus.BuyPets;
import ui.component.menus.Home;

import java.io.IOException;
import java.util.Optional;

public class Root extends VBox
{

    private Compositor displayCompositor;

    private Compositor menuCompositor;


    @FXML
    private Text userMessage;

    @FXML
    private StackPane display;

    @FXML
    private HBox menus;

    public Root()
    {
        Component.load("Root.fxml", this);
    }

    //Welcome sequence
    @FXML
    protected void initialize()
        throws IOException
    {
        User user = new User(collectInput("Enter your name:", "Welcome to Virtual Pets!"));

        user.getPets().addListener((ListChangeListener)(change -> {
            while(change.next())
            {
                for (Object obj : change.getAddedSubList())
                {
                    Pet pet = (Pet) obj;

                    pet.hungerStatProperty().addListener((changeStat ->
                    {
                        if (pet.getHungerStat() <= 0)
                        {
                            changeMessage(pet.getName() + " died from hunger!");
                            pauseForMessage("What would you like to do now?");
                            menuCompositor.transitionTo(new Home(this, user));
                        }
                    }));

                    pet.thirstStatProperty().addListener((changeStat ->
                    {
                        if (pet.getThirstStat() <= 0)
                        {
                            changeMessage(pet.getName() + " died from thirst!");
                            pauseForMessage("What would you like to do now?");
                            menuCompositor.transitionTo(new Home(this, user));
                        }
                    }));

                    pet.hygieneStatProperty().addListener((changeStat ->
                    {
                        if (pet.getHygieneStat() == 0)
                        {
                            changeMessage(pet.getName() + " is very dirty! You should give them a bath!");
                        }
                    }));
                }
            }
        }));

        LivingRoom livingRoom = new LivingRoom(user);

        displayCompositor = new Compositor(display);
        displayCompositor.transitionTo(livingRoom);

        menuCompositor = new Compositor(menus);
        menuCompositor.transitionTo(new BuyPets(this, user));

        changeMessage("Hello " + user.getName() + "! You need to buy your first pet!");
    }


    private String collectInput(String message, String header)
    {
        TextInputDialog input = new TextInputDialog();
        input.setTitle("Virtual Pets");
        input.setHeaderText(header);
        input.setContentText(message);
        Optional<String> name = input.showAndWait();

        if (!header.equals("Welcome to Virtual Pets!"))
        {
            changeMessage("What would you like to do now?");
        }
        return name.orElse("");
    }

    public void changeMessage(String message)
    {
        userMessage.setText(message);
    }

    public void transitionDisplay(Node node)
    {
        displayCompositor.transitionTo(node);
    }

    public void transitionMenu(Node node)
    {
        menuCompositor.transitionTo(node);
    }

    public boolean checkPrice(int price, User user)
    {
        if(!user.canAfford(price))
        {
            changeMessage("You don't have enough money for that!");
            menuCompositor.transitionTo(new Home(this, user));
            pauseForMessage("What would you like to do now?");
            return false;
        }
        return true;
    }

    public void pauseForMessage(String message)
    {
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(2000),
                ae -> changeMessage(message))
        );

        timeline.play();
    }
}
