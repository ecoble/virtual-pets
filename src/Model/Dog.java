package model;

public class Dog extends Pet
{
    public final static int price = 100;
    public final static int numUnits = 4;

    public Dog()
    {
        super("dog");
    }

    public PetType getType()
    {
        return PetType.LAND;
    }

    public int getNumUnits()
    {
        return numUnits;
    }
}