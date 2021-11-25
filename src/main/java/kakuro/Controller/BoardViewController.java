package kakuro.Controller;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import kakuro.View.BoardView;
import kakuro.View.Cell.NumberCell;
import kakuro.View.SelectBoardView;

public class BoardViewController {
    List<NumberCell> cellList;
    List<Button> menuButtons;
    List<Button> numberPadButtons;
    Scene scene;
    BoardView boardView;
    Thread thread;
    Stack<HashMap<NumberCell,HashMap<String,Boolean>>> undoStack;
    Boolean isPencil;
    Button pencil;


    public BoardViewController(
            List<NumberCell> cellList,
            List<Button> menuButtons,
            List<Button> numberPadButtons,
            Scene scene,
            BoardView boardView,
            Button pencil
    )
    {
        this.isPencil = false;
        this.undoStack = new Stack<>();
        this.pencil = pencil;
        this.cellList = cellList;
        this.boardView = boardView;
        this.menuButtons = menuButtons;
        this.numberPadButtons = numberPadButtons;
        this.scene = scene;
    }

    public void addControllers()
    {
        setUpNumberPadController();
        setUpEraseController();
        setUpGoBackController();
        setUpRestartController();
        setUpSceneController();
        setUpUndoController();
        setUpCellController();
        setUpPencilController();
    }


    //TODO split setUpUndoController into more functions
    private void setUpNumberPadController()
    {
        for (Button number : this.numberPadButtons)
        {
           number.setOnMouseClicked(mouseEvent ->
           {
               NumberCell selected = getFocusedCell();
               HashMap<NumberCell,HashMap<String, Boolean>> cellUndoMove = new HashMap<>();
               HashMap<String, Boolean> prevIsPencil = new HashMap<>();
               if (this.isPencil)
               {

                   if (selected.cell.getText().length() < 4)
                   {
                       prevIsPencil.put(selected.cell.getText(), selected.isNumberPencil);
                       if (!selected.isNumberPencil)
                       {
                           selected.updateText("",isPencil);
                       }
                       cellUndoMove.put(selected,prevIsPencil);
                       undoStack.add(cellUndoMove);
                       selected.updateText(selected.cell.getText() + number.getText() ,isPencil);
                   }
               }
               else
               {
                   prevIsPencil.put(selected.cell.getText(), selected.isNumberPencil);
                   cellUndoMove.put(selected,prevIsPencil);
                   undoStack.add(cellUndoMove);
                   selected.updateText(number.getText(),this.isPencil);
               }
               boardView.setListViewItems();
               markBoard();
//               checkSum(selected);
           });
        }
    }

    //TODO split setUpUndoController into more functions
    private void setUpUndoController()
    {
        Button undo = getMenuButton("undo");
        undo.setOnMouseClicked(mouseEvent ->
        {
            if (!undoStack.isEmpty())
            {
                HashMap<NumberCell,HashMap<String, Boolean>> pop = undoStack.pop();
                Optional<NumberCell> cell = pop.keySet().stream().findFirst();
                Optional<HashMap<String,Boolean>> prevIsPenMap = pop.values().stream().findFirst();
                Optional<Boolean> isPen = prevIsPenMap.get().values().stream().findFirst();
                Optional<String> prev = prevIsPenMap.get().keySet().stream().findFirst();
                if (isPen.isPresent())
                {
                    NumberCell numCell = cell.get();
                    if (isPen.get())
                    {
                        String prevMove = prev.get();
                        numCell.cell.setFont(Font.font("verdana" , 7));
                        numCell.updateText(prevMove, true);
                    }
                    else
                    {
                        String prevMove = prev.get();
                        numCell.cell.setFont(Font.font("verdana" , FontWeight.EXTRA_BOLD, 10));
                        numCell.updateText(prevMove, false);
                        numCell.cell.setText(prevMove);
                    }

                }
                boardView.setListViewItems();
                markBoard();
//                checkSum(cell.get());
            }
        });
    }

    public void setUpGoBackController()
    {
        Button undo = getMenuButton("goBack");
        undo.setOnMouseClicked(mouseEvent ->
        {
            BoardView.close();
            SelectBoardView.show();
        });
    }

    private void setUpEraseController()
    {
        Button erase = getMenuButton("erase");
        erase.setOnMouseClicked(mouseEvent ->
        {
            NumberCell selected = getFocusedCell();
            selected.updateText("", false);
            boardView.setListViewItems();
            markBoard();
//            checkSum(selected);
        });
    }
    private void setUpRestartController()
    {
        Button restart = getMenuButton("restart");
        restart.setOnMouseClicked(mouseEvent ->
        {
            for (NumberCell cell : cellList)
            {
                cell.updateText("", false);
            }
            markBoard();
            this.undoStack.clear();
        });
    }

    private void setUpCellController()
    {
        for (NumberCell cell : cellList)
        {
            cell.cell.setOnMouseClicked(mouseEvent ->
            {
                boardView.setListViewItems();
                markBoard();
//                checkSum(cell);
            });
        }
    }

    private void setUpSceneController()
    {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<Event>() {
            String keyPressed = "";
            @Override
            public void handle(Event event) {
                thread = new Thread( () -> {
                    stop(50);
                    String keyEvent = event.toString().substring(1,event.toString().length()-1);
                    String [] eventList = keyEvent.split(",");
                    String [] keyList = eventList[eventList.length -1 ].split("=");
                    if (keyList.length > 1)
                    {
                        keyPressed = keyList[1].trim();
                    }
                    else
                    {
                        keyPressed = "key does nothing";
                    }
                    if (isArrowKey(keyPressed)) {
                        Platform.runLater(() -> boardView.setListViewItems());
                    }
                });
                thread.start();
            }
        });
    }

    private void setUpPencilController()
    {
        this.pencil.setOnMouseClicked(mouseEvent ->
        {
            this.isPencil = !this.isPencil;
            if(this.isPencil)
            {
                this.pencil.setStyle("-fx-border-color: black");
            }
            else
            {
                this.pencil.setStyle("-fx-border-color: none");
            }
        });
    }

    //TODO pass selected cell instead, could be more efficient
    private void markBoard()
    {
        for(NumberCell cell : cellList)
        {
            checkSum(cell);
        }
    }

    private void checkSum(NumberCell cell)
    {
        int currentColumnSum = getRowColumnSum(cell, cell.columnCells)[0];
        int currentRowSum = getRowColumnSum(cell, cell.rowCells)[0];
        int nearRowSize = getRowColumnSum(cell, cell.rowCells)[1];
        int nearColumnSize = getRowColumnSum(cell, cell.columnCells)[1];
        int rowSum = cell.rowSum;
        int columnSum = cell.columnSum;

        checkRowColumn(currentColumnSum, columnSum, cell.columnCells, cell.columnSize, nearColumnSize);
        checkRowColumn(currentRowSum, rowSum, cell.rowCells, cell.rowSize, nearRowSize);
    }

    private void checkRowColumn(int currentSum, int sum, List<int[]> nearCells, int size, int nearCellSize)
    {
        if (currentSum == sum  && size == nearCellSize )
        {
            cellChangeOutLineColour("green", nearCells, true);

        }
        else
        {
            if (currentSum >= sum)
            {
                cellChangeOutLineColour("red", nearCells, false);
            }
            else
            {
                cellChangeOutLineColour("none", nearCells, false);
            }
        }
    }

    private int[] getRowColumnSum(NumberCell cell ,List<int[]> cellsIndex)
    {
        int currentSum = 0;
        int numSize = 0;
        if(!cell.isNumberPencil)
        {
            if (cell.number.length() == 1)
            {
                currentSum = Integer.parseInt(cell.number);
                numSize++;
            }
        }

        for (int [] index : cellsIndex)
        {
            int indexI = index[0];
            int indexJ = index[1];
            NumberCell neighbourCell = boardView.board.cellBoard2D[indexI][indexJ];
            if (neighbourCell.number.length() == 1 && !neighbourCell.isNumberPencil)
            {
                int cellNum = Integer.parseInt(neighbourCell.number);
                currentSum += cellNum;
                numSize++;
            }
        }

        return new int[]{currentSum, numSize};
    }

    private void cellChangeOutLineColour(String colour, List<int[]> cellToChange, Boolean isGreen)
    {
        for (int [] index : cellToChange)
        {
            int indexI = index[0];
            int indexJ = index[1];
            NumberCell newColor = boardView.board.cellBoard2D[indexI][indexJ];
            newColor.cell.setStyle("-fx-border-color: " + colour);
            newColor.isGreen = isGreen;
        }
    }

    private void stop(long milli){
        try
        {
            Thread.sleep(milli);
        }
        catch (InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    private Button getMenuButton(String buttonId)
    {
        Button result = null;
        for (Button btn :menuButtons)
        {
            if (btn.getId().equals(buttonId))
            {
                result = btn;
            }
        }
        return result;
    }


    private NumberCell getFocusedCell()
    {
        NumberCell result = null;
        for (NumberCell cell : cellList)
        {
            if (cell.cell.isFocused())
            {
                result = cell;
            }
        }
        return result;
    }


    private boolean isArrowKey(String key)
    {
        switch (key)
        {
            case "DOWN":
            case "UP":
            case "RIGHT":
            case "LEFT":
                return true;
        }
        return false;
    }
}