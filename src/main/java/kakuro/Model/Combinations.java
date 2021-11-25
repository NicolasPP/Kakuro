package kakuro.Model;

import java.util.*;

public class Combinations
{
    int numberOfDigits;
    public HashMap<Integer, List<String[]>> sumToDigits;

    public Combinations(int numberOfDigits, HashMap<Integer, List<String[]>> sumToDigits)
    {
        this.numberOfDigits = numberOfDigits;
        this.sumToDigits = sumToDigits;
    }

}
