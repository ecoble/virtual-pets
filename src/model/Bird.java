package model;

public class Bird extends Pet
{
    public final static int price = 200;
    public final static int numUnits = 2;

    public Bird()
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

    public void train()
    {
        addSkillPoints(4);
    }
}