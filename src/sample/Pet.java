package sample;
public class Pet
{
    private String name;
    private String species;
    final static int foodPrice = 5;

    public Pet(String name, String species)
    {
        this.name = name;
        this.species = species;
    }

    public String getName()
    {
        return name;
    }

    public String getSpecies()
    {
        return species;
    }
}