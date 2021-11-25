package kakuro.Model;

import kakuro.View.Cell.NumberCell;

public class Board {
    int column_num;
    int row_num;
    public String [] board;
    public String [][] board2D;
    public NumberCell [][] cellBoard2D;

    public Board(int column_num , int row_num , String [] board)
    {
        this.column_num = column_num;
        this.row_num = row_num;
        this.board = board;
        this.cellBoard2D = new NumberCell[column_num][row_num];
        this.board2D = setUp2DBoard();
    }

    public String[][] setUp2DBoard()
    {
        int height = board.length;
        int length = board[0].split(",").length;
        String [][] b2D = new String[height][length];

        for (int i = 0; i < height; i++)
        {
            String [] rowCells = board[i].split(",");
            for (int j = 0; j < length; j++)
            {
                String cell = rowCells[j].trim();
                b2D[i][j] = cell;
            }
        }

        return b2D;
    }

    public void addCellToBoard(NumberCell cell, int iIndex, int jIndex)
    {
        cellBoard2D[iIndex][jIndex] = cell;
    }
}
