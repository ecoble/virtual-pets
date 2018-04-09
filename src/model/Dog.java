package model;

import commands.FoodType;

public class Dog extends Pet
{
    public final static int price = 100;
    public final static int numUnits = 4;
    public final static int foodPrice = 2;

    public Dog()
    {
        super("dog");
    }

    public PetType getType()
    {
        return PetType.LAND;
    }

    public FoodType getFoodType()
    {
        return FoodType.DOG;
    }

    public int getNumUnits()
    {
        return numUnits;
    }

    public void train()
    {
        addSkillPoints(5);
    }
}