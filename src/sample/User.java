package sample;

import Commands.PurchaseCommand;

import java.util.ArrayList;

public class User{

    private String name;
    private int money;
    private int food;
    private ArrayList<Pet> pets;


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

    public boolean canAfford(int price)
    {
        if(money >= price)
        {
            return true;
        }

        return false;
    }

    public void purchase(PurchaseCommand command)
    {
        if(!canAfford(command.getPrice()))
        {
            return;
        }

        command.execute(this);
        withdrawMoney(command.getPrice());
    }

    public void addPet(Pet pet)
    {
        pets.add(pet);
    }
}