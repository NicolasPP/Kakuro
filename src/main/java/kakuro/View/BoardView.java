package kakuro.View;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import kakuro.Controller.BoardViewController;
import kakuro.Model.Board;
import kakuro.Model.CombinationData;
import kakuro.View.Cell.BlankCell;
import kakuro.View.Cell.NumberCell;
import kakuro.View.Cell.SumCell;
import kakuro.util.Resources;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoardView
{
    static BoardView boardViewPage;
    public Stage stage;
    public TilePane boardGrid;
    TilePane menuGrid;
    TilePane numberPad;
    TilePane possibleSums;
    TilePane pencilPane;
    public AnchorPane backGround;
    Button pencilButton;
    ListView columnCombination;
    ListView rowCombination;
    public Scene window;
    double windowHeight;
    double windowWidth;
    double noOverlapSpace = 3.0;
    double anchorGap = 10.0;
    public int height;
    public int width;
    int spacing = 3;
    int buttonSize = 60;
    int cellSize = 30;
    int imgSize = 40;
    List<NumberCell> cellList;
    List<Button> menuButtons;
    List<Button> numberPadButtons;
    final String NUMBER_CELL = "x";
    final String BLANK_CELL = "x/x";
    final String UNDO = "undo.png";
    final String GO_BACK = "goBack.png";
    final String ERASE = "erase.png";
    final String RESTART = "restart.png";
    public Board board;


    //FIXME redesign page for largest board configuration
    //TODO change TilePane possible sums to contain canvas as child, rather than buttons.
    //TODO add win check and add pop up window for win
    //TODO add timer
    //TODO add file to remember score
    //TODO add progression bar
    //TODO add win animation
    //TODO add pop up window for reset button asking for confirmation

    public BoardView(Board board, int height, int width)
    {
        this.board = board;
        this.height = height;
        this.width = width;
        this.boardGrid = setUpBoardGrid();
        this.menuGrid = setUpMenuGrid();
        this.numberPad = setUpNumberPad();
        this.pencilPane = setUpPencilPane();
        this.rowCombination = setUpRowListView();
        this.columnCombination = setUpColumnListView();
        this.possibleSums = setUpPossibleSums();
        this.backGround = setUpBackGround();
        this.windowHeight = calculateWindowDimension(height);
        this.windowWidth = calculateWindowDimension(width);
        this.window = setUpWindow();
        this.stage = setUpStage();
    }

    private TilePane setUpBoardGrid()
    {
        String [] testList = this.board.board;

        int indexI = 0;

        TilePane grid = new TilePane();
        setUpTilePaneLayout(grid , this.height , this.width);


        cellList = new ArrayList<>();
        for(int i = 0; i < height; i++)
        {
            String [] cellsTest = testList[indexI].split(",");
            int indexJ = 0;
            for (String testing : cellsTest)
            {
                if(testing.trim().equals(NUMBER_CELL))
                {
                    createNumberCell(grid, board.board2D, indexI, indexJ);
                }
                else if (testing.trim().equals(BLANK_CELL))
                {
                    createBlankCell(grid);
                }
                else
                {
                    createSumCell(grid, testing);
                }
                indexJ++;
            }
            indexI++;
        }
        return grid;
    }

    private TilePane setUpMenuGrid()
    {

        menuButtons = new ArrayList<>();
        int size = 2;

        TilePane menuGrid = new TilePane();
        setUpTilePaneLayout(menuGrid , size , size);

        Button undo = setUpMenuButtons("undo");
        Button erase = setUpMenuButtons("erase");
        Button goBack = setUpMenuButtons("goBack");
        Button restart = setUpMenuButtons("restart");

        addButtonImage(Resources.getIconPath(UNDO), undo, imgSize, imgSize);
        addButtonImage(Resources.getIconPath(GO_BACK), goBack, imgSize, imgSize);
        addButtonImage(Resources.getIconPath(ERASE), erase, imgSize, imgSize);
        addButtonImage(Resources.getIconPath(RESTART), restart, imgSize, imgSize);

        menuGrid.getChildren().addAll(undo, erase, goBack, restart);
        menuButtons.add(undo);
        menuButtons.add(erase);
        menuButtons.add(goBack);
        menuButtons.add(restart);

        return menuGrid;
    }

    private TilePane setUpNumberPad()
    {
        numberPadButtons = new ArrayList<>();
        TilePane numbers = new TilePane();
        setUpTilePaneLayout(numbers, 3, 3);

        for (int i = 0; i < 9; i++)
        {
            Button number = new Button(i + 1 + "");
            number.setPrefHeight(cellSize + 6);
            number.setPrefWidth(cellSize + 6);
            number.setFocusTraversable(false);
            numberPadButtons.add(number);
            numbers.getChildren().add(number);
        }

        return numbers;
    }

    private TilePane setUpPencilPane()
    {
        TilePane penPane = new TilePane();
        this.pencilButton = new Button();
        this.pencilButton.setFocusTraversable(false);
        addButtonImage("icons/pencil.png", pencilButton, 50 , 50);
        setUpTilePaneLayout(penPane, 1,1);
        penPane.getChildren().add(pencilButton);
        return penPane;
    }

    private TilePane setUpPossibleSums()
    {
        TilePane sums = new TilePane();
        setUpTilePaneLayout(sums , 2 , 1);
       sums.getChildren().addAll(
               this.columnCombination,
               this.rowCombination
       );
        return sums;
    }

    private ListView setUpRowListView()
    {
        ListView rowSum = new ListView();
        double menuGridWidthOrHeight = buttonSize * 2;
        double boardSpace = ( ( height + 1 ) * spacing );
        double boardGridWidthOrHeight = (this.boardGrid.getTileWidth() * height);
        double listViewHeight = (boardSpace + boardGridWidthOrHeight - anchorGap * 2) / 2;
        rowSum.setPrefWidth(menuGridWidthOrHeight + spacing - anchorGap);
        rowSum.setPrefHeight(listViewHeight);
        rowSum.setFocusTraversable(false);
        return rowSum;
    }

    public void setListViewItems()
    {
        NumberCell cell = getFocusedCell();
        if (cell != null)
        {

            this.columnCombination.getItems().clear();
            this.rowCombination.getItems().clear();

            rowCombination.getItems().add("Row" + " " + cell.rowSum);
            columnCombination.getItems().add("Column" + " " + cell.columnSum);
            CombinationData combination = new CombinationData();
            int rowSize = cell.rowSize;
            int rowSum = cell.rowSum;
            int columnSize = cell.columnSize;
            int columnSum = cell.columnSum;


            List<String []> rowCombinations = combination.combinationData.get(rowSize).sumToDigits.get(rowSum);
            List<String []> columnCombinations = combination.combinationData.get(columnSize).sumToDigits.get(columnSum);

            List<String> rowDigits = getDigitList(cell.rowCells, cell);
            List<String> columnDigits = getDigitList(cell.columnCells, cell);

            List<String[]> filteredRowCombinations = filterCombinations(rowCombinations, rowDigits);
            List<String[]> filteredColumnCombinations = filterCombinations(columnCombinations, columnDigits);

            addCombinationsToListView(filteredRowCombinations,rowCombination);
            addCombinationsToListView(filteredColumnCombinations,columnCombination);
        }
    }

    private ListView setUpColumnListView()
    {
        ListView columnSum = new ListView();
        double menuGridWidthOrHeight = buttonSize * 2;
        double boardSpace = ( ( height + 1 ) * spacing );
        double boardGridWidthOrHeight = (this.boardGrid.getTileWidth() * height);
        double listViewHeight = (boardSpace + boardGridWidthOrHeight - anchorGap * 2) / 2;
        columnSum.setPrefWidth(menuGridWidthOrHeight + spacing - anchorGap);
        columnSum.setPrefHeight(listViewHeight);
        columnSum.setFocusTraversable(false);
        return columnSum;
    }

    private AnchorPane setUpBackGround()
    {
        AnchorPane anchor = new AnchorPane();
        anchor.getChildren().addAll(
                this.boardGrid,
                this.menuGrid,
                this.numberPad,
                this.possibleSums,
                this.pencilPane
        );

        AnchorPane.setLeftAnchor(this.boardGrid, anchorGap);
        AnchorPane.setTopAnchor(this.boardGrid, anchorGap);

        AnchorPane.setBottomAnchor(this.menuGrid,anchorGap);
        AnchorPane.setRightAnchor(this.menuGrid, anchorGap);

        double menuGridSize = buttonSize * 2;
        double menuSpacing = ( 3 * spacing );
        double boardGridHeight = (this.boardGrid.getTileWidth() * height);
        double boardSpaceHeight = ( ( height + 1 ) * spacing );
        AnchorPane.setBottomAnchor(this.numberPad, anchorGap);
        AnchorPane.setRightAnchor(this.numberPad, anchorGap * 2 + menuGridSize + menuSpacing );
        AnchorPane.setTopAnchor(this.numberPad,anchorGap * 2 + boardGridHeight + boardSpaceHeight );

        AnchorPane.setTopAnchor(this.possibleSums, anchorGap);
        AnchorPane.setRightAnchor(this.possibleSums, anchorGap);

        double numberPadSpacing = spacing * 4;
        double numberPadSize = (cellSize + 6) * 3;
        AnchorPane.setBottomAnchor(this.pencilPane, anchorGap);
        AnchorPane.setRightAnchor(this.pencilPane, (anchorGap * 3) + numberPadSpacing + menuSpacing + menuGridSize + numberPadSize);

        return anchor;
    }

    private Scene setUpWindow()
    {
        return new Scene(
                this.backGround,
                windowWidth,
                windowHeight
        );
    }

    private Stage setUpStage()
    {
        Stage st = new Stage();
        st.setScene(window);
        st.setTitle("Kakuro");
        st.setResizable(false);

        new BoardViewController(
                this.cellList,
                this.menuButtons,
                this.numberPadButtons,
                this.window,
                this,
                this.pencilButton
        ).addControllers();
        return st;
    }


    private List<String[]> filterCombinations(List<String[]>combinations, List<String> digits)
    {
        List<String []> filteredCombinations = new ArrayList<>();


        for (String [] combination : combinations)
        {
            {
                if (digits.size() == 0)
                {
                    filteredCombinations.add(combination);
                }
                else
                    if (checkCombinationDigits(digits,combination))
                    {
                        filteredCombinations.add(combination);
                    }
            }
        }


        return filteredCombinations;
    }

    private boolean checkCombinationDigits(List<String> digits, String [] combination)
    {

        boolean [] boolList = new boolean[digits.size()];
        int boolIndex = 0;


        for (String digit : digits)
        {
            boolean isDig = false;
            for (String combDig : combination)
            {
                if (combDig.trim().equals(digit.trim()))
                {
                    isDig = true;
                    break;
                }
            }
            boolList[boolIndex] = isDig;
            boolIndex++;
        }

        for (boolean present : boolList)
            if (!present)
                return false;
        return true;
    }


    private List<String>  getDigitList(List<int[]> cellsIndex, NumberCell cell)
    {
        List<String> digits = new ArrayList<>();

        if (cell.number.length() == 1 && !cell.isNumberPencil)
        {
            digits.add(cell.number.trim());
        }

        for (int [] index : cellsIndex)
        {
            int indexI = index[0];
            int indexJ = index[1];

            NumberCell neighbourCell = this.board.cellBoard2D[indexI][indexJ];
            if (neighbourCell.number.length() == 1 && !neighbourCell.isNumberPencil)
            {
                String cellNum = neighbourCell.number.trim();
                digits.add(cellNum);
            }
        }

        return digits;
    }

    private void addCombinationsToListView(List<String []> combinations,ListView rowOrColumn)
    {
        for (String [] ColumnComb : combinations)
        {
            String combinationString = Arrays.toString(ColumnComb);
            rowOrColumn.getItems().add(combinationString);
        }
    }

    private void createNumberCell(TilePane grid , String [][] board, int indexI, int indexJ)
    {
        NumberCell numberCell = new NumberCell(cellSize, board, indexI, indexJ);
        this.board.addCellToBoard(numberCell,indexI,indexJ);
        cellList.add(numberCell);
        grid.getChildren().add(numberCell.cell);
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

    private void createBlankCell(TilePane grid)
    {
        BlankCell blankCell = new BlankCell(cellSize);
        grid.getChildren().add(blankCell.blank);
    }

    private void createSumCell(TilePane grid, String cell)
    {
        String [] split = cell.trim().split("/");
        SumCell sumCell = new SumCell(cellSize ,split[0], split[1]);
        Canvas canvas = sumCell.sumCanvas;
        grid.getChildren().add(canvas);
    }

    private void addButtonImage(String fileName, Button btn, int imageSizeHeight, int imageSizeWidth)
    {
        Image img = new Image(fileName);
        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(imageSizeHeight);
        imgView.setFitWidth(imageSizeWidth);
        imgView.setPreserveRatio(false);
        btn.setGraphic(imgView);
    }

    private Button setUpMenuButtons(String id)
    {
        Tooltip help = new Tooltip(id);
        Button b = new Button();
        help.setTextAlignment(TextAlignment.CENTER);
        b.setTooltip(help);
        Tooltip.install(b, help);
        b.setId(id);
        b.setPrefHeight(buttonSize);
        b.setPrefWidth(buttonSize);
        b.setFocusTraversable(false);
        return b;
    }

    private double calculateWindowDimension(int widthOrHeight)
    {
        double boardSpace = ( ( widthOrHeight + 1 ) * spacing );
        double menuSpace = ( 3 * spacing );
        double boardGridWidthOrHeight = (this.boardGrid.getTileWidth() * widthOrHeight);
        double menuGridWidthOrHeight = buttonSize * 2;
        return boardGridWidthOrHeight + menuGridWidthOrHeight + boardSpace + menuSpace + anchorGap * 2 + noOverlapSpace;
    }

    private void setUpTilePaneLayout(TilePane grid , int rowNum , int columnNum)
    {
        grid.setVgap(spacing);
        grid.setHgap(spacing);
        grid.setPadding(new Insets(spacing, spacing, spacing, spacing));
        grid.setPrefRows(rowNum);
        grid.setPrefColumns(columnNum);
        grid.setStyle("-fx-border-color: black");
    }

    public static void show()
    {
        boardViewPage.stage.show();
    }

    public static void close()
    {
        boardViewPage.stage.close();
    }

    public static void create(Board board, int height, int width)
    {
        boardViewPage = new BoardView(board, height, width);
    }
}