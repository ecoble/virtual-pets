package model;

import commands.FoodType;

public class Fish extends Pet
{
    public final static int price = 5;
    public final static int numUnits = 8;
    public final static int foodPrice = 1;

    public Fish()
    {
        super("fish");
    }

    public PetType getType()
    {
        return PetType.FISH;
    }

    public FoodType getFoodType()
    {
        return FoodType.FISH;
    }

    public int getNumUnits()
    {
        return numUnits;
    }

    public void train(){}
}