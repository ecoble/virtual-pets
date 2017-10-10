package Model;

public class Bird extends Pet
{
    public final static int price = 200;
    public final static int numUnits = 2;

    public Bird(String name)
    {
        super("bird");
    }

    public PetType getType()
    {
        return PetType.BIRD;
    }

    public int getNumUnits()
    {
        return numUnits;
    }
}