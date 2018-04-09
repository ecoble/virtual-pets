package model;

import commands.FoodType;

public class Cat extends Pet
{
    public final static int price = 50;
    public final static int numUnits = 2;
    public final static int foodPrice = 1;

    public Cat()
    {
        super("cat");
    }

    public PetType getType()
    {
        return PetType.LAND;
    }

    public FoodType getFoodType()
    {
        return FoodType.CAT;
    }

    public int getNumUnits()
    {
        return numUnits;
    }

    public void train()
    {
        addSkillPoints(1);
    }
}