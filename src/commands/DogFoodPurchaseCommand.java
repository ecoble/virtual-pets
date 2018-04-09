package commands;

import model.User;

public class DogFoodPurchaseCommand extends FoodPurchaseCommand
{
    public DogFoodPurchaseCommand()
    {
        super(2);
    }

    public void execute(User user)
    {
        user.addFood(FoodType.DOG);
    }
}
