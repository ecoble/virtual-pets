package Model;

import Model.Pet;

public class Rabbit extends Pet
{
    public final static int price = 50;
    public final static int numUnits = 1;

    public Rabbit(String name)
    {
        super("rabbit");
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