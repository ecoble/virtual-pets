package sample;

import java.util.ArrayList;

public class User{

    private String name;
    private int money;
    ArrayList<Pet> petList;


    public User(String name)
    {
        this.name = name;
        this.money = 2000;
    }

    public int getMoney()
    {
        return money;
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

}