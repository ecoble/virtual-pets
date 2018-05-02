package ui.component;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class MenuItem
{
    private String label;
    private EventHandler<ActionEvent> onActionHandler;

    public MenuItem(String label, EventHandler<ActionEvent> onActionHandler)
    {
        this.label = label;
        this.onActionHandler = onActionHandler;
    }

    public String label()
    {
        return label;
    }

    public EventHandler<ActionEvent> onActionHandler()
    {
        return onActionHandler;
    }
}
