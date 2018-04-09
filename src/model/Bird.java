package model;

import commands.FoodType;

public class Bird extends Pet
{
    public final static int price = 200;
    public final static int numUnits = 8;
    public final static int foodPrice = 3;

    public Bird()
    {
        super("bird");
    }

    public PetType getType()
    {
        return PetType.BIRD;
    }

    public FoodType getFoodType()
    {
        return FoodType.BIRD;
    }

    public int getNumUnits()
    {
        return numUnits;
    }

    public void train()
    {
        addSkillPoints(4);
    }
}