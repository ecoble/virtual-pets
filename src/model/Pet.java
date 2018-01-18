package model;

import java.util.*;

public abstract class Pet
{
    private String name;
    private String species;
    private int hungerStat;
    private int thirstStat;
    private int hygieneStat;
    private List<Runnable> feedCallbacks;
    private List<Runnable> waterCallbacks;
    final static public int foodPrice = 5;

    public Pet(String species)
    {
        this.species = species;
        this.feedCallbacks = new ArrayList<Runnable>();
        this.waterCallbacks = new ArrayList<Runnable>();
        this.hungerStat = 100;
        this.thirstStat = 100;
        this.hygieneStat = 100;
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

    public int getHungerStat()
    {
        return hungerStat;
    }

    public int getThirstStat()
    {
        return thirstStat;
    }

    public int getHygieneStat()
    {
        return hygieneStat;
    }

}