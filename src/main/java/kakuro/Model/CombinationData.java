package kakuro.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class CombinationData
{
    public HashMap<Integer, Combinations> combinationData;
    static private final String COMBINATION = "combinations.txt";
    static private final String FILEPATH = "C:\\Users\\nicol\\Documents\\Projects\\Kakuro\\src\\main\\resources\\";


    public CombinationData()
    {
        this.combinationData = populate();
    }


    private Scanner readFile() throws FileNotFoundException
    {
        File COMBINATION_FILE = new File(FILEPATH + CombinationData.COMBINATION);
        return new Scanner(COMBINATION_FILE);
    }

    private int[] getFirstLineNumbers(String firstLine)
    {
        int[] digitCombinationNum = new int[2];
        String[] combinationList = firstLine.split(",");
        digitCombinationNum[0] = Integer.parseInt(combinationList[0].trim());
        digitCombinationNum[1] = Integer.parseInt(combinationList[1].trim());
        return digitCombinationNum;
    }

    private int getSum(String sumCombinationString)
    {
        String sumSubstring = sumCombinationString.substring(0, 2);
        return Integer.parseInt(sumSubstring.trim());
    }

    private List<String[]> getCombinationsList(String sumCombinationString)
    {
        List<String[]> stringListCombs = new ArrayList<>();
        int stringLen = sumCombinationString.length();
        String combinationsSubstring = sumCombinationString.substring(4, stringLen);
        String[] sumCombinationList = combinationsSubstring.split(",");
        for (String comb : sumCombinationList)
        {
            String[] digitList = comb.split("\\+");
            stringListCombs.add(digitList);
        }
        return stringListCombs;
    }


    private HashMap<Integer, Combinations> populate()
    {
        HashMap<Integer, Combinations> digitNumToCombinations = new HashMap<>();
        try {
            Scanner fileReader = readFile();
            while (fileReader.hasNextLine())
            {
                int[] digitCombinationNum = getFirstLineNumbers(fileReader.nextLine());
                int digitNum = digitCombinationNum[0];
                int combinationNum = digitCombinationNum[1];
                HashMap<Integer, List<String[]>> sumToDigits = new HashMap<>();
                for (int i = 0; i < combinationNum; i++)
                {
                    String sumCombinationString = fileReader.nextLine();
                    int sum = getSum(sumCombinationString);
                    List<String[]> stringListCombs = getCombinationsList(sumCombinationString);
                    sumToDigits.put(sum, stringListCombs);
                }
                Combinations comb = new Combinations(digitNum, sumToDigits);
                digitNumToCombinations.put(digitNum, comb);
            }
            return digitNumToCombinations;
        }
        catch (FileNotFoundException FlNf)
        {
            System.out.println("File not found");
            FlNf.printStackTrace();
        }

        return digitNumToCombinations;
    }
}
