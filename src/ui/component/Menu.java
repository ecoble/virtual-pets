package ui.component;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Menu extends VBox
{
    @FXML
    protected Pane buttonRow;
    @FXML protected Button backButton;

    private MenuItem backItem;
    private List<MenuItem> items;

    public Menu(MenuItem backItem, MenuItem... items)
    {
        this.backItem = backItem;
        this.items = Arrays.asList(items);

        Component.load("Menu.fxml", this);
    }

    @FXML
    protected void initialize()
    {
        buttonRow.getChildren().addAll(items.stream().map(item -> {
            Button button = new Button(item.label());
            button.setOnAction(item.onActionHandler());

            return button;
        }).collect(Collectors.toList()));

        backButton.setText(backItem.label());
        backButton.setOnAction(backItem.onActionHandler());
    }
}
