package model;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

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

        Timeline hungerTimeline = new Timeline(new KeyFrame(
                Duration.millis(1000),
                ae -> hungerStat --)
        );

        hungerTimeline.setCycleCount(Animation.INDEFINITE);
        hungerTimeline.play();

        Timeline thirstTimeline = new Timeline(new KeyFrame(
                Duration.millis(1000),
                ae -> thirstStat--)
        );

        thirstTimeline.setCycleCount(Animation.INDEFINITE);
        thirstTimeline.play();

        Timeline hygieneTimeline = new Timeline(new KeyFrame(
                Duration.millis(1000),
                ae -> hygieneStat--)
        );

        hygieneTimeline.setCycleCount(Animation.INDEFINITE);
        hygieneTimeline.play();
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

        hungerStat = 100;
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

        thirstStat = 100;
    }

    public void onGiveWater(Runnable callback)
    {
        waterCallbacks.add(callback);
    }

    public void wash()
    {
        hygieneStat = 100;
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