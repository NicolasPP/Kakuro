package kakuro.Model;

import kakuro.util.Resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class BoardData
{
    List<Board>  easyBoards;
    List<Board>  mediumBoards;
    List<Board>  difficultBoards;
    private final String EASY = "easy_board_data.txt";
    private final String MEDIUM = "medium_board_data.txt";
    private final String DIFFICULT = "difficult_board_data.txt";

    public BoardData()
    {
        this.easyBoards = populate(EASY);
        this.mediumBoards = populate(MEDIUM);
        this.difficultBoards = populate(DIFFICULT);
    }

    public HashMap<String, List<Board>> getBoardsByDimensions(String difficulty)
    {
        List<Board> boards;
        if (difficulty.equals(EASY))
        {
            boards = easyBoards;
        }
        else if(difficulty.equals(MEDIUM))
        {
            boards = mediumBoards;
        }
        else
        {
            boards = difficultBoards;
        }
        return generateHashMap(boards);

    }

    private HashMap<String, List<Board>> generateHashMap(List<Board> boards)
    {
        HashMap<String, List<Board>> dimensionBoards = new HashMap<>();
        for (Board board :  boards)
        {
            String dimension = board.column_num + "x" + board.row_num;
            List<Board> tempList;
            if (dimensionBoards.containsKey(dimension))
            {
                tempList = dimensionBoards.get(dimension);

            }
            else
            {
                tempList = new ArrayList<>();
            }
            tempList.add(board);
            dimensionBoards.put(dimension, tempList);
        }
        return dimensionBoards;
    }

    private Scanner readFile(String fileName) throws FileNotFoundException
    {
        File DIFFICULT_BOARD = null;
        try {
            DIFFICULT_BOARD = Resources.getPath( fileName);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return new Scanner(DIFFICULT_BOARD);
    }

    private String trimCellString(String cellString)
    {
        cellString = cellString.trim();
        int len = cellString.length();
        String trimmedCellString = cellString.substring(3,len - 1);
        len  = trimmedCellString.length();
        String test2 = trimmedCellString.substring(len-1, len);
        if (test2.equals(")"))
        {
            trimmedCellString = trimmedCellString.substring(0,len -1 );
        }
        return trimmedCellString;
    }

    private int [] getDimensions(String firstLine)
    {
        String [] elements  =  firstLine.split(",");
        int height =Integer.parseInt(elements[3]);
        int width =Integer.parseInt(elements[4]);
        return new int[]{width, height};
    }

    private List<Board> populate(String fileName)
    {
        List<Board> boards = new ArrayList<>();
        try
        {
            Scanner fileReader = readFile(fileName);
            while (fileReader.hasNextLine())
            {
                String firstLine = fileReader.nextLine();
                int [] dimensions = getDimensions(firstLine);
                int row_number = dimensions[0]; // height
                int column_number = dimensions[1]; // width
                String [] data = new String[column_number];
                for (int i = 0; i < column_number + 2 ; i++)
                {
                    String cellString = fileReader.nextLine();
                    if (i != 0 && i != column_number + 1)
                    {
                        cellString = trimCellString(cellString);
                        data[i-1] = cellString;
                    }
                }
                Board board = new Board(column_number,row_number,data);
                boards.add(board);

            }
            fileReader.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("file not found");
            e.printStackTrace();
        }
        return boards;
    }
}
