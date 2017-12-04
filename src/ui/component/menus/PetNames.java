package ui.component.menus;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.*;
import ui.component.Component;
import ui.component.Root;
import ui.component.menus.Interact;

/**
 * Created by M5sp on 10/24/17
 */
public class PetNames extends VBox
{
    private Root root;
    private User user;
    private Pet pet;

    @FXML
    private HBox petNameBox;

    public PetNames(Root root, User user)
    {
        this.root = root;
        this.user = user;

        Component.load("PetNames.fxml", this);
    }

    @FXML
    protected void initialize()
    {
        for(Pet pet : user.getPets())
        {
            createButton(pet.getName());
        }
    }

    @FXML
    protected void cancel(MouseEvent event)
    {
        root.transitionMenu(new Home(root, user));
        root.changeMessage("What would you like to do now?");
    }

    public void createButton(String name)
    {
        Button nameButton = new Button(name);

        nameButton.setOnMouseClicked(event ->
        {
            Button b = (Button) event.getSource();
            for(Pet pet : user.getPets())
            {
                if(pet.getName().equals(b.getText()))
                {
                    this.pet = pet;
                    root.transitionMenu(new Interact(root, user, this.pet));
                }
            }
            root.changeMessage("What would you like to do with " + pet.getName() + "?");
        });

        petNameBox.getChildren().add(nameButton);
    }
}
