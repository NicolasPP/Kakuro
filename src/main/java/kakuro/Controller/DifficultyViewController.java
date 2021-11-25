package kakuro.Controller;

import javafx.scene.control.Button;
import kakuro.View.DifficultyView;
import kakuro.View.MenuView;
import kakuro.View.SelectDimensionView;


public class DifficultyViewController {
    Button easy;
    Button medium;
    Button difficult;
    Button goBack;
    private final String EASY = "data/easy_board_data.txt";
    private final String MEDIUM = "data/medium_board_data.txt";
    private final String DIFFICULT = "data/difficult_board_data.txt";

    public DifficultyViewController
            (
                    Button easy,
                    Button medium,
                    Button difficult,
                    Button goBack
    )

    {
        this.easy = easy;
        this.medium = medium;
        this.difficult = difficult;
        this.goBack = goBack;
    }

    public void addControllers()
    {
        setUpEasyButton();
        setUpMediumButton();
        setUpDifficultButton();
        setUpGoBackButton();
    }


    private void setUpEasyButton()
    {
        this.easy.setOnMouseClicked(mouseEvent ->
        {
            DifficultyView.close();
            SelectDimensionView.create(EASY);
            SelectDimensionView.show();
        });
    }

    private void setUpMediumButton()
    {
        this.medium.setOnMouseClicked(mouseEvent ->
        {
            DifficultyView.close();
            SelectDimensionView.create(MEDIUM);
            SelectDimensionView.show();
        });
    }

    private void setUpDifficultButton()
    {
        this.difficult.setOnMouseClicked(mouseEvent ->
        {
            DifficultyView.close();
            SelectDimensionView.create(DIFFICULT);
            SelectDimensionView.show();
        });
    }

    private void setUpGoBackButton()
    {
        this.goBack.setOnMouseClicked(mouseEvent ->
        {
            DifficultyView.close();
            MenuView.show();
        });
    }
}
