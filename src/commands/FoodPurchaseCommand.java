package commands;

import model.User;

/**
 * Created by M5sp on 9/26/17.
 */
public abstract class FoodPurchaseCommand implements PurchaseCommand {
    private int price;

    public FoodPurchaseCommand(int price)
    {
        this.price = price;
    }

    public int getPrice()
    {
        return price;
    }

    public abstract void execute(User user);
}
