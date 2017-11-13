package ui.component;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import ui.Compositor;

import java.io.IOException;
import java.util.Optional;

public class Root extends VBox
{
    public User user;

    public String currPetName;

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
        user = new User(collectInput("Enter your name:", "Welcome to Virtual Pets!"));

        displayCompositor = new Compositor(display);
        displayCompositor.transitionTo(new LivingRoom(user));

        menuCompositor = new Compositor(menus);
        menuCompositor.transitionTo(new BuyPets(this));

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

    public boolean checkPrice(int price)
    {
        if(!user.canAfford(price))
        {
            changeMessage("You don't have enough money for that!");
            menuCompositor.transitionTo(new Home(this));
            pauseForMessage("What would you like to do now?");
            return false;
        }
        return true;
    }

    public void pauseForMessage(String message)
    {
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(1500),
                ae -> changeMessage(message))
        );

        timeline.play();
    }
}
