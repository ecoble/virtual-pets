package tests;

import model.NumberGenerator;

public class MinNumberGenerator implements NumberGenerator
{
    public int nextInt(int min, int max)
    {
        return min;
    }
}
