package sample;

import javafx.fxml.FXML;

public class Dog extends Pet
{
    public final static int price = 100;

    public Dog(String name)
    {
        super(name, "dog");
    }
}