package model;

import commands.PurchaseCommand;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class User{

    private String name;
    private int money;
    private int food;
    private ObservableList<Pet> pets;
    private int landPetUnits;
    private int birdUnits;
    private int fishUnits;
    public final static int maxUnits = 8;


    public User(String name)
    {
        this.name = name;
        this.money = 2000;
        pets = FXCollections.observableArrayList();
    }

    public int getMoney()
    {
        return money;
    }

    public int getFood()
    {
        return food;
    }

    public void addFood()
    {
        food++;
    }

    public void withdrawMoney(int lostMoney)
    {
        money = money - lostMoney;
    }

    public void addMoney(int gainMoney)
    {
        money = money + gainMoney;
    }

    public String getName()
    {
        return name;
    }

    public ObservableList<Pet> getPets()
    {
        return new ReadOnlyListWrapper(pets);
    }

    public void addLandPetUnits(int units)
    {
        landPetUnits += units;
    }

    public void addBirdUnits(int units)
    {
        birdUnits += units;
    }

    public void addFishUnits(int units)
    {
        fishUnits += units;
    }

    public boolean canAfford(int price)
    {
        if(money >= price)
        {
            return true;
        }

        return false;
    }

    public boolean hasSpace(Pet pet)
    {
        switch(pet.getType()) {
            case LAND:
                if(landPetUnits + pet.getNumUnits() > maxUnits)
                {
                    return false;
                }
                break;
            case BIRD:
                if(birdUnits + pet.getNumUnits() > maxUnits)
                {
                    return false;
                }
                break;
            case FISH:
                if(fishUnits + pet.getNumUnits() > maxUnits)
                {
                    return false;
                }
                break;
        }

        return true;

    }

    public void purchase(PurchaseCommand command)
    {
        if(!canAfford(command.getPrice()))
        {
            throw new IllegalStateException("Attempted to overspend");
        }

        command.execute(this);
        withdrawMoney(command.getPrice());
    }

    public void addPet(Pet pet)
    {
        pets.add(pet);
    }
}