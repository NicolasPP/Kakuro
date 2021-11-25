package kakuro;

import javafx.application.Application;
import javafx.stage.Stage;
import kakuro.View.MenuView;


public class KakuroApp extends Application
{
    @Override
    public void init() throws Exception
    {
        super.init();
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        Stage menu = MenuView.create(stage);
        menu.show();
    }

    @Override
    public void stop() throws Exception
    {
        super.stop();
    }

    public static void main(String[] args)
    {
        launch();
    }
}
