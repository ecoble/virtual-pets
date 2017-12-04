package model;

import java.util.*;

public abstract class Pet
{
    private String name;
    private String species;
    private List<Runnable> feedCallbacks;
    private List<Runnable> waterCallbacks;
    final static public int foodPrice = 5;

    public Pet(String species)
    {
        this.species = species;
        this.feedCallbacks = new ArrayList<Runnable>();
        this.waterCallbacks = new ArrayList<Runnable>();
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

    public abstract PetType getType();

    public abstract int getNumUnits();

    public void feed()
    {
        for (Runnable callback: feedCallbacks)
        {
            callback.run();
        }
    }

    public void onFeed(Runnable callback)
    {
        feedCallbacks.add(callback);
    }

    public void giveWater()
    {
        for(Runnable callback: waterCallbacks)
        {
            callback.run();
        }
    }

    public void onGiveWater(Runnable callback)
    {
        waterCallbacks.add(callback);
    }




}