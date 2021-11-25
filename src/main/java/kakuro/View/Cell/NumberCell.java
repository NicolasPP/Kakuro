package kakuro.View.Cell;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.util.ArrayList;
import java.util.List;

public class NumberCell
{
    public Button cell;
    int size;
    int indexI;
    int indexJ;
    public int rowSize;
    public int columnSize;
    public int columnSum;
    public int rowSum;
    public Boolean isNumberPencil = false;
    public List<int[]> rowCells = new ArrayList<>();
    public List<int[]> columnCells = new ArrayList<>();
    public String [][] board;
    final String NUMBER_CELL = "x";
    final String BLANK_CELL = "x/x";
    public String number = "";
    public boolean isGreen = false;

    //TODO figure out how to add multi color to string.
    //TODO change button to canvas, this way I can use multi coloured numbers.

    public NumberCell(int size , String [][] board, int indexI, int indexJ)
    {
        this.indexI = indexI;
        this.indexJ = indexJ;
        this.board = board;
        this.size = size;
        calculateCellInfo();
        this.cell = setUpButton();
    }

    private Button setUpButton()
    {
        Button cellButton = new Button(number);
        cellButton.setPrefHeight(size);
        cellButton.setPrefWidth(size);
        return cellButton;
    }

    private void calculateCellInfo()
    {
        int rowCountRight = getRowRightCount();
        int rowCountLeft = getRowLeftCount();
        int columnCountUp = getColumnUpCount();
        int columnCountDown = getColumnDownCount();

        this.rowSize = rowCountLeft + rowCountRight + 1;
        this.columnSize = columnCountUp + columnCountDown + 1;
    }

    public void updateText(String newString, boolean isPen)
    {
        this.number = newString;
        if(isPen)
        {
            this.isNumberPencil = true;
            this.cell.setFont(Font.font("verdana" , 7));
        }
        else
        {
            this.isNumberPencil = false;
            this.cell.setFont(Font.font("verdana" , FontWeight.EXTRA_BOLD, 10));
        }
        this.cell.setText(this.number);
    }


    private int getRowRightCount()
    {
        int length = board[0].length - 1;
        int jIndex;
        int rightCount = 0;

        for (int i = 1; i < 9; i++)
        {
            jIndex = indexJ + i;
            if (jIndex > length)
            {
                break;
            }
            else if (board[indexI][jIndex].equals(NUMBER_CELL))
            {
                rowCells.add(new int[]{indexI, jIndex});
                rightCount++;
            }
            else
            {
                break;
            }
        }
        return rightCount;
    }

    private int getColumnDownCount()
    {
        int height = board.length - 1;
        int iIndex;
        int downCount = 0;

        for (int i = 1; i < 9; i++)
        {
            iIndex = indexI + i;
            if (iIndex > height)
            {
                break;
            }
            else if (board[iIndex][indexJ].equals(NUMBER_CELL))
            {
                columnCells.add(new int[]{iIndex, indexJ});
                downCount++;
            }
            else
            {
                break;
            }
        }
        return downCount;
    }

    private int getRowLeftCount()
    {
        int jIndex;
        String [] rowSum = new String[2];
        int leftCount = 0;

        for (int j = 1; j < 9; j++)
        {

            jIndex = indexJ - j;
            if (jIndex < 0)
            {
                break;
            }
            else if (board[indexI][jIndex].equals(NUMBER_CELL))
            {
                rowCells.add(new int[]{indexI, jIndex});
                leftCount++;
            }
            else
            {
                if (!board[indexI][jIndex].equals(BLANK_CELL))
                {
                    rowSum = board[indexI][jIndex].split("/");
                    break;
                }
            }
        }
        if (rowSum[1] != null)
        {
            this.rowSum = Integer.parseInt(rowSum[1]);
        }
        else
        {
            this.rowSum = 0;
        }
        return leftCount;
    }


    private int getColumnUpCount()
    {
        int iIndex;
        String [] colSum = new String[2];
        int upCount = 0;

        for (int i = 1; i < 9; i++)
        {

            iIndex = indexI - i;
            if (iIndex < 0)
            {
                break;
            }
            else if (board[iIndex][indexJ].equals(NUMBER_CELL))
            {
                columnCells.add(new int[]{iIndex, indexJ});
                upCount++;
            }
            else
            {
                if (!board[iIndex][indexJ].equals(BLANK_CELL))
                {
                    colSum = board[iIndex][indexJ].split("/");
                    break;
                }
            }
        }
        if (colSum[0] != null)
        {
            this.columnSum = Integer.parseInt(colSum[0]);
        }
        else
        {
            this.columnSum = 0;
        }
        return upCount;
    }
}
