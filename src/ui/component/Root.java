package ui.component;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import model.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import ui.Compositor;
import ui.component.environments.LivingRoom;

import java.io.IOException;
import java.util.Optional;

public class Root extends VBox
{

    private Compositor compositor;

    private User user;

    private String filePath;

    private boolean isNew;

    @FXML
    private StackPane display;

    public Root()
    {
        isNew = true;
        Component.load("Root.fxml", this);
    }

    public Root(User user, String filePath)
    {
        this.user = user;
        this.filePath = filePath;
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

        compositor = new Compositor(display);

        LivingRoom livingRoom;

        if(isNew)
        {
            livingRoom = new LivingRoom(user, this, "Hello " + user.getName() + "! You need to buy your first pet!");
        }
        else
        {
            livingRoom = new LivingRoom(user, this, "Welcome back " + user.getName() + "! What would you like to do?");
        }

        compositor.transitionTo(livingRoom);
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

    public void transition(Node node)
    {
        compositor.transitionTo(node);
    }

    private boolean checkNameLength(String name)
    {
        return name.length() > 30;
    }

    public boolean getIsNew()
    {
        return isNew;
    }

    public String getFilePath()
    {
        return filePath;
    }
}
