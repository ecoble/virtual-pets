package commands;

import model.User;

public class BirdFoodPurchaseCommand extends FoodPurchaseCommand
{
    public BirdFoodPurchaseCommand()
    {
        super(3);
    }

    public void execute(User user)
    {
        user.addFood(FoodType.BIRD);
    }
}
