package kakuro.Controller;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import kakuro.Model.Board;
import kakuro.Model.BoardData;
import kakuro.View.DifficultyView;
import kakuro.View.SelectBoardView;
import kakuro.View.SelectDimensionView;
import java.util.List;

public class SelectDimensionViewController
{
    ListView dimensionList;
    Button goBack;
    String difficulty;
    BoardData boardData;

    public SelectDimensionViewController(ListView dimensionList, Button goBack,String difficulty, BoardData boardData)
    {
        this.boardData = boardData;
        this.dimensionList = dimensionList;
        this.goBack = goBack;
        this.difficulty = difficulty;
    }

    public void addControllers()
    {
        setUpGoBackController();
        setUpDimensionListController();
    }

    private void setUpGoBackController()
    {
        this.goBack.setOnMouseClicked(mouseEvent ->
        {
            SelectDimensionView.close();
            DifficultyView.show();
        });
    }

    private void setUpDimensionListController()
    {
        this.dimensionList.setOnMouseClicked(mouseEvent ->
        {
            String id = dimensionList.getSelectionModel().getSelectedItem().toString();
            String [] dimList = id.split(":");
            String dim = dimList[0].trim();
            System.out.println(this.difficulty);
            List<Board> dimensionBoards = boardData.getBoardsByDimensions(this.difficulty).get(dim);
            SelectDimensionView.close();
            SelectBoardView.create(dimensionBoards, id, dimensionBoards);
            SelectBoardView.show();
        });
    }
}
