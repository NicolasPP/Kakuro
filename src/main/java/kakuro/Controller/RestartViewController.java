package kakuro.Controller;

import javafx.scene.control.Button;
import kakuro.View.BoardView;
import kakuro.View.Cell.NumberCell;
import kakuro.View.RestartView;

public class RestartViewController
{
    Button yes;
    Button no;

    public RestartViewController(Button yes, Button no)
    {
        this.yes = yes;
        this.no = no;
    }

    public void addControllers()
    {
        setUpYesController();
        setUpNoController();
    }

    private void setUpYesController()
    {
        this.yes.setOnMouseClicked(mouseEvent ->
        {
            for (NumberCell cell : BoardView.boardViewPage.cellList)
            {
                cell.updateText("", false);
            }
            RestartView.close();
            BoardView.boardViewPage.controller.enableControlls();
            BoardView.boardViewPage.controller.undoStack.clear();
        });
    }
    private void setUpNoController()
    {
        this.no.setOnMouseClicked(mouseEvent ->
        {
            RestartView.close();
            BoardView.boardViewPage.controller.enableControlls();
        });

    }

}
