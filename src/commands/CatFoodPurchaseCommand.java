package commands;

import model.User;

public class CatFoodPurchaseCommand extends FoodPurchaseCommand
{
    public CatFoodPurchaseCommand()
    {
        super(1);
    }

    public void execute(User user)
    {
        user.addFood(FoodType.CAT);
    }
}
