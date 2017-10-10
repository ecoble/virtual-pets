package Model;

public class Fish extends Pet
{
    public final static int price = 5;
    public final static int numUnits = 8;

    public Fish(String name)
    {
        super("fish");
    }

    public PetType getType()
    {
        return PetType.FISH;
    }

    public int getNumUnits()
    {
        return numUnits;
    }
}