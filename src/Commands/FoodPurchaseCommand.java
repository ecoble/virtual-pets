package Commands;

import UI.User;

/**
 * Created by M5sp on 9/26/17.
 */
public class FoodPurchaseCommand implements PurchaseCommand {
    private int price;

    public FoodPurchaseCommand(int price)
    {
        this.price = price;
    }

    public int getPrice()
    {
        return price;
    }

    public void execute(User user)
    {
        user.addFood();
    }
}
