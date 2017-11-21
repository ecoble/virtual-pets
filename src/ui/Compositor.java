package ui;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * Created by M5sp on 11/2/17.
 */
public class Compositor
{
    private Pane root;

    public Compositor(Pane root)
    {
        this.root = root;
    }

    public void transitionTo(Node node)
    {
        root.getChildren().clear();
        root.getChildren().add(node);
        node.setVisible(true);
        node.setManaged(true);
    }
}
