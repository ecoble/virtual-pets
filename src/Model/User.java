package Model;

import Commands.PurchaseCommand;
import Model.Pet;

import java.util.ArrayList;

public class User{

    private String name;
    private int money;
    private int food;
    private ArrayList<Pet> pets;
    private int landPetUnits;
    private int birdUnits;
    private int fishUnits;
    public final static int maxUnits = 8;


    public User(String name)
    {
        this.name = name;
        this.money = 1000;
        pets = new ArrayList<Pet>();
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

    public ArrayList<Pet> getPets()
    {
        return pets;
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
        switch(pet.getSpecies()) {
            case "dog":
                if(landPetUnits + Dog.numUnits > maxUnits)
                {
                    return false;
                }
                break;
            case "cat":
                if(landPetUnits + Cat.numUnits > maxUnits)
                {
                    return false;
                }
                break;
            case "rabbit":
                if(landPetUnits + Rabbit.numUnits > maxUnits)
                {
                    return false;
                }
                break;
            case "bird":
                if(birdUnits + Bird.numUnits > maxUnits)
                {
                    return false;
                }
                break;
            case "fish":
                if(fishUnits + Fish.numUnits > maxUnits)
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