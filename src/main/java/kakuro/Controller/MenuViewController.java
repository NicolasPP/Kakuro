package kakuro.Controller;

import kakuro.View.DifficultyView;
import kakuro.View.MenuView;

public class MenuViewController {
    MenuView menu;

    public MenuViewController(
            MenuView menu
    )
    {
        this.menu = menu;
    }

    public void setUpControllers()
    {
        setUpPlayButton();
        setUpQuitButton();
    }

    public void setUpPlayButton()
    {
        menu.play.setOnMouseClicked(mouseEvent ->
        {
            DifficultyView.create();
            menu.stage.close();
            DifficultyView.show();

        });
    }

    public void setUpQuitButton()
    {
        menu.quit.setOnMouseClicked(mouseEvent ->
                MenuView.close());
    }
}
