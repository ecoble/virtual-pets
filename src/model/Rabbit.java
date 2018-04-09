package model;

import commands.FoodType;

public class Rabbit extends Pet
{
    public final static int price = 50;
    public final static int numUnits = 1;
    public final static int foodPrice = 3;

    public Rabbit()
    {
        super("rabbit");
    }

    public PetType getType()
    {
        return PetType.LAND;
    }

    public FoodType getFoodType()
    {
        return FoodType.RABBIT;
    }

    public int getNumUnits()
    {
        return numUnits;
    }

    public void train()
    {
        addSkillPoints(2);
    }
}