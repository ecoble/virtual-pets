package sample;
public class Pet
{
    private String name;
    private String species;

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