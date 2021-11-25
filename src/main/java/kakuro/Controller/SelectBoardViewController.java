package kakuro.Controller;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import kakuro.Model.Board;
import kakuro.View.BoardView;
import kakuro.View.SelectBoardView;
import kakuro.View.SelectDimensionView;
import java.util.List;

public class SelectBoardViewController
{
    ListView boardListView;
    Button goBack;
    String dimension;
    List<Board> data;

    public SelectBoardViewController(ListView boardListView, Button goBack, String dimension, List<Board> data)
    {
        this.data = data;
        this.dimension = dimension;
        this.boardListView = boardListView;
        this.goBack = goBack;
    }

    public void addControllers()
    {
        setUpListViewController();
        setUpGoBackController();
    }

    private void setUpListViewController()
    {
        this.boardListView.setOnMouseClicked(mouseEvent ->
        {
            String [] indexString = boardListView.getSelectionModel().getSelectedItem().toString().split(",");
            int index = Integer.parseInt(indexString[0].substring(8).trim());
            int height = Integer.parseInt(dimension.split(":")[0].split("x")[0].trim());
            int width = Integer.parseInt(dimension.split(":")[0].split("x")[1].trim());
            Board board = data.get(index);
            SelectBoardView.close();
            BoardView.create(board, height, width);
            BoardView.show();
        });
    }
    private void setUpGoBackController()
    {
        this.goBack.setOnMouseClicked(mouseEvent ->
        {
            SelectBoardView.close();
            SelectDimensionView.show();
        });
    }

}
