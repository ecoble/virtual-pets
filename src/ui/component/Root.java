package ui.component;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
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

    private User user;

    private String fileName;

    private boolean isNew;


    @FXML
    private StackPane display;

    @FXML
    private HBox menus;

    public Root()
    {
        isNew = true;
        Component.load("Root.fxml", this);
    }

    public Root(User user, String fileName)
    {
        this.user = user;
        this.fileName = fileName;
        isNew = false;
        Component.load("Root.fxml", this);
    }

    //Welcome sequence
    @FXML
    protected void initialize()
        throws IOException
    {
        if(isNew)
        {
            String name = collectInput("Enter your name (30 char limit):", "Welcome to Virtual Pets!");
            while (checkNameLength(name))
            {
                name = collectInput("Your name was too long!\nEnter your name (30 char limit):", "Welcome to Virtual Pets!");
            }
            user = new User(name);
        }

        menuCompositor = new Compositor(menus);
        displayCompositor = new Compositor(display);

        LivingRoom livingRoom;

        if(isNew)
        {
            livingRoom = new LivingRoom(user, this, "Hello " + user.getName() + "! You need to buy your first pet!");
            //menuCompositor.transitionTo(new BuyPets(this, user));
            //changeMessage("Hello " + user.getName() + "! You need to buy your first pet!");
        }
        else
        {
            livingRoom = new LivingRoom(user, this, "Welcome back " + user.getName() + "! What would you like to do?");
            //menuCompositor.transitionTo(new Home(this, user));
            //changeMessage("Welcome back " + user.getName() + "! What would you like to do?");
        }

        displayCompositor.transitionTo(livingRoom);
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

    public void transitionDisplay(Node node)
    {
        displayCompositor.transitionTo(node);
    }

    public void transitionMenu(Node node)
    {
        menuCompositor.transitionTo(node);
    }

    private boolean checkNameLength(String name)
    {
        return name.length() > 30;
    }

    public boolean getIsNew()
    {
        return isNew;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void clearMenu()
    {
        menus.getChildren().clear();
    }
}
