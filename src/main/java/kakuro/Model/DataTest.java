package kakuro.Model;

import java.util.*;

public class DataTest {
    public static void main(String[] args) {
        final String EASY = "data/easy_board_data.txt";
        final String MEDIUM = "data/medium_board_data.txt";
        final String DIFFICULT = "data/difficult_board_data.txt";
        BoardData boardData = new BoardData();
        CombinationData combs = new CombinationData();
        List<String []> combinations = combs.combinationData.get(5).sumToDigits.get(34);

//        List<String> fontFamilies = Font.getFamilies();
//        List<String> fontNames    = Font.getFontNames();
//
//        for(String item : fontFamilies) {
//            System.out.println(item);
//        }
//        for(String item : fontNames) {
//            System.out.println(item);
//        }



//
//        for(String [] list : combinations)
//        {
//            for(String num : list)
//            {
//                System.out.print(num.trim() + " ");
//            }
//            System.out.println();
//        }


        // test for get board by dimensions



//        LinkedHashMap<String, Integer> dimensionCardinality = new LinkedHashMap<>();
//        for (kakuro.Model.Board board :  boardData.mediumBoards)
//        {
//            String dimension = Integer.toString(board.column_num) + Integer.toString(board.row_num);
//            if (dimensionCardinality.containsKey(dimension))
//            {
//                dimensionCardinality.put(dimension, dimensionCardinality.get(dimension) + 1);
//            }
//            else
//            {
//                dimensionCardinality.put(dimension, 1);
//            }
//        }
//        for (Map.Entry<String, Integer> entry : dimensionCardinality.entrySet()) {
//            String key = entry.getKey();
//            int value = entry.getValue();
//            System.out.print("Dimension :");
//            System.out.print(key);
//            System.out.print("    ");
//            System.out.print(value);
//            System.out.println();
//        }



        // Test for kakuro.Model.Board data


//        HashMap<String, List<kakuro.Model.Board>> dimensionBoards = boardData.getBoardsByDimensions(EASY);
//        for(Map.Entry<String, List<kakuro.Model.Board>> entry : dimensionBoards.entrySet())
//        {
//            String key = entry.getKey();
//            List<kakuro.Model.Board> bData =  entry.getValue();
//            System.out.print(key + "         ");
//            System.out.println(bData.size());
//            for(kakuro.Model.Board b : bData)
//            {
//                for (String cellString : b.board)
//                {
////                    System.out.println(cellString);
//                }
////                System.out.println("===============================");
//            }

        // test for cells


//        ArrayList<ArrayList<Object>>cellsList = boardData.easyBoards.get(0).cellsList;
//        String [] test = boardData.easyBoards.get(1).board;

//        for (String cell : test)
//        {
//            System.out.println(cell + " ");
//        }

//        for (int i = 0 ; i < boardData.easyBoards.get(0).row_num ; i++)
//        {
//            for (int j = 0 ; j < boardData.easyBoards.get(0).column_num ; j++)
//            {
//                Object cell = cellsList.get(i).get(j).print();
//                System.out.print(cell + " " );
//            }
//            System.out.println();
//        }

//    }
    }
}
