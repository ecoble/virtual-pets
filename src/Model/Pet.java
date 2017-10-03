package Model;
public class Pet
{
    private String name;
    private String species;
    final static public int foodPrice = 5;

    public Pet(String species)
    {
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

    public void setName(String name)
    {
        this.name = name;
    }

}