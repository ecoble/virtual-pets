package model;

import java.util.Random;

public class RandomNumberGenerator implements NumberGenerator
{
    public int nextInt(int min, int max)
    {
        Random rand = new Random();
        return rand.nextInt(max - min) + min;
    }
}
