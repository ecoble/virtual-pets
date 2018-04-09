package commands;

import model.User;

public class FishFoodPurchaseCommand extends FoodPurchaseCommand
{
    public FishFoodPurchaseCommand()
    {
        super(1);
    }

    public void execute(User user)
    {
        user.addFood(FoodType.FISH);
    }
}
