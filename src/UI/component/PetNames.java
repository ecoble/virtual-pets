package ui.component;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import model.*;

/**
 * Created by M5sp on 10/24/17.
 */
public class PetNames extends HBox
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
