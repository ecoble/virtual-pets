package commands;

import model.User;

/**
 * Created by M5sp on 9/22/17.
 */
public interface PurchaseCommand {
    int getPrice();
    void execute(User user);
}
