package Model;

public class Cat extends Pet
{
    public final static int price = 50;
    public final static int numUnits = 2;

    public Cat(String name)
    {
        super("cat");
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