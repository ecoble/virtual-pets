package tests;

import commands.FoodType;
import model.Dog;
import model.User;
import org.junit.jupiter.api.Test;
import ui.component.environments.Competition;

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

    @Test
    public void didCatch_true_when_max()
    {
        Dog dog = new Dog();
        assertTrue(dog.didCatch(new MaxNumberGenerator()));
    }

    @Test
    public void didCatch_false_when_min()
    {
        Dog dog = new Dog();
        assertFalse(dog.didCatch(new MinNumberGenerator()));
    }
}
