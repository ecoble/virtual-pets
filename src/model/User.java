package model;

import commands.FoodType;
import commands.PurchaseCommand;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User{

    private String name;
    private int money;
    private Map<FoodType, Integer> foodInventory = new HashMap<FoodType, Integer>();
    private ObservableList<Pet> pets;
    private int landPetUnits;
    private int birdUnits;
    private int fishUnits;
    public final static int maxUnits = 7;

    private User()
    {
        this("");
    }

    public User(String name)
    {
        this.name = name;
        this.money = 2000;
        pets = FXCollections.observableArrayList();

        Timeline time = new Timeline(new KeyFrame(
                Duration.millis(100),
                ae -> depletePetStats()
        ));

        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    public int getMoney()
    {
        return money;
    }

    public int getFood(FoodType food)
    {
        return foodInventory.getOrDefault(food, 0);
    }

    public void addFood(FoodType food)
    {
        foodInventory.put(food, foodInventory.getOrDefault(food, 0) + 1);
    }

    public void withdrawFood(FoodType food)
    {
        if(foodInventory.getOrDefault(food, 0) <= 0)
        {
            throw new IllegalStateException();
        }

        foodInventory.put(food, foodInventory.get(food) - 1);
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

    public void removePet(Pet pet)
    {
        pets.remove(pet);

        if(pet.getType() == PetType.BIRD)
        {
            birdUnits -= pet.getNumUnits();
        }
        else if(pet.getType() == PetType.FISH)
        {
            fishUnits -= pet.getNumUnits();
        }
        else if(pet.getType() == PetType.LAND)
        {
            landPetUnits -= pet.getNumUnits();
        }
    }

    private void depletePetStats()
    {
        for(int i = 0; i < pets.size(); i++)
        {
            Pet pet = pets.get(i);
            pet.depleteHunger(0.001);

            if(!pet.getSpecies().equals("fish"))
            {
                pet.depleteThirst(0.0015);
                //hygiene depletion must be a divisor of 100
                pet.depleteHygiene(0.0001);
            }

            if(pet.getHungerStat() <= 0)
            {
                removePet(pet);
                i--;
            }
            else if(pet.getThirstStat() <= 0)
            {
                removePet(pet);
                i--;
            }
        }
    }
}