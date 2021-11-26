package kakuro.Controller;

import javafx.scene.control.Button;
import kakuro.View.BoardView;
import kakuro.View.SelectBoardView;
import kakuro.View.WinView;

public class WinViewController
{
    Button goBack;

    public WinViewController(Button goBack)
    {
        this.goBack = goBack;
    }

    public void addControllers()
    {
        setUpGoBackController();
    }

    private void setUpGoBackController()
    {
        this.goBack.setOnMouseClicked(mouseEvent ->
        {
            SelectBoardView.show();
            WinView.close();
            BoardView.boardViewPage.controller.enableControlls();

        });
    }
}
