package tests;

import model.NumberGenerator;

public class MaxNumberGenerator implements NumberGenerator
{
    public int nextInt(int min, int max)
    {
        return max;
    }
}
