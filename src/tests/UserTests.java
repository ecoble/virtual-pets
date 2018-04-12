package tests;

import commands.FoodType;
import model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTests
{
    @Test
    public void getFood_when_zero()
    {
        User user = new User("User1");
        assertEquals(0, user.getFood(FoodType.DOG));
    }

    @Test
    public void addFood_when_zero()
    {
        User user = new User("User1");
        user.addFood(FoodType.DOG);
        assertEquals(1, user.getFood(FoodType.DOG));
    }

    @Test
    public void withdrawFood_when_zero()
    {
        User user = new User("User1");
        assertThrows(IllegalStateException.class, () -> {
            user.withdrawFood(FoodType.DOG);
        });
    }

    @Test
    public void withdrawFood_when_two()
    {
        User user = new User("User1");
        user.addFood(FoodType.DOG);
        user.addFood(FoodType.DOG);
        user.withdrawFood(FoodType.DOG);
        assertEquals(1, user.getFood(FoodType.DOG));
    }
}
