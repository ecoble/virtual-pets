package commands;

import model.User;

public class RabbitFoodPurchaseCommand extends FoodPurchaseCommand
{
    public RabbitFoodPurchaseCommand()
    {
        super(3);
    }

    public void execute(User user)
    {
        user.addFood(FoodType.RABBIT);
    }
}
