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

    @FXML
    private HBox petNameBox;

    public PetNames(Root root)
    {
        this.root = root;
        this.user = root.user;

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
        root.transitionMenu(new Home(root));
        root.changeMessage("What would you like to do now?");
    }

    public void createButton(String name)
    {
        Button nameButton = new Button(name);

        nameButton.setOnMouseClicked(event ->
        {
            root.transitionMenu(new Interact(root));
            Button b = (Button) event.getSource();
            root.currPetName = b.getText();
            root.changeMessage("What would you like to do with " + root.currPetName + "?");
        });

        petNameBox.getChildren().add(nameButton);
    }
}
