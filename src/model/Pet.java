package model;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableDoubleValue;
import javafx.util.Duration;

import java.util.*;

public abstract class Pet
{
    private String name;
    private String species;
    private int skillPoints;
    private DoubleProperty hungerStat;
    private DoubleProperty thirstStat;
    private DoubleProperty hygieneStat;
    private transient List<Runnable> feedCallbacks;
    private transient List<Runnable> waterCallbacks;
    final static public int foodPrice = 5;

    public Pet(String species)
    {
        this.species = species;
        this.skillPoints = 0;
        this.feedCallbacks = new ArrayList<Runnable>();
        this.waterCallbacks = new ArrayList<Runnable>();
        this.hungerStat = new SimpleDoubleProperty(100.0);
        this.thirstStat = new SimpleDoubleProperty(100.0);
        this.hygieneStat = new SimpleDoubleProperty(100.0);
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

    public int getSkillPoints()
    {
        return skillPoints;
    }

    public void addSkillPoints(int points)
    {
        skillPoints += points;
    }

    public abstract PetType getType();

    public abstract int getNumUnits();

    public void feed()
    {
        for (Runnable callback: feedCallbacks)
        {
            callback.run();
        }

        hungerStat.set(100);
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

        thirstStat.set(100);
    }

    public void onGiveWater(Runnable callback)
    {
        waterCallbacks.add(callback);
    }

    public void wash()
    {
        hygieneStat.set(100);
    }

    public double getHungerStat()
    {
        return hungerStat.get();
    }

    public double getThirstStat()
    {
        return thirstStat.get();
    }

    public double getHygieneStat()
    {
        return hygieneStat.get();
    }

    public void depleteHunger(double deplete)
    {
        double newValue = hungerStat.get() - deplete;
        hungerStat.set(newValue);
    }

    public void depleteThirst(double deplete)
    {
        double newValue = thirstStat.get() - deplete;
        thirstStat.set(newValue);
    }

    public void depleteHygiene(double deplete)
    {
        double newValue = hygieneStat.get() - deplete;
        hygieneStat.set(newValue);
    }

    public DoubleProperty hungerStatProperty()
    {
        return hungerStat;
    }

    public DoubleProperty thirstStatProperty()
    {
        return thirstStat;
    }

    public DoubleProperty hygieneStatProperty()
    {
        return hygieneStat;
    }

}