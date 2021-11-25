package kakuro.View;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import kakuro.Controller.MenuViewController;

public class MenuView
{
    public static MenuView menu;
    public Stage stage;
    final int spacing = 10;
    public AnchorPane anchor;
    public Scene window;
    public Button play;
    public Button quit;
    final private VBox page;

    //TODO add title to menu
    //TODO make menu a bit bigger, maybe same size as listView pages

    public MenuView(Stage stage)
    {
        this.stage = stage;
        this.page = setUpPage();
        this.anchor = setUpAnchor();
        this.window = setUpScene();
        setUpStage();
    }

    private VBox setUpPage()
    {
        VBox menuPane = new VBox();
        setUpVboxPaneLayout(menuPane);

        play = new Button("Play");
        quit = new Button("Quit");

        menuPane.getChildren().addAll
                (
                        play,
                        quit
                );

        return menuPane;
    }
    public AnchorPane setUpAnchor()
    {
        AnchorPane ac = new AnchorPane();

        AnchorPane.setRightAnchor(page , (double) spacing);
        AnchorPane.setLeftAnchor(page , (double) spacing);
        AnchorPane.setTopAnchor(page , (double) spacing);
        AnchorPane.setBottomAnchor(page , (double) spacing);

        ac.getChildren().addAll
                (
                        this.page
                );
        return ac;
    }

    private Scene setUpScene()
    {
        new MenuViewController(this).setUpControllers();
        return new Scene(anchor);
    }

    public void setUpStage()
    {
        stage.setScene(window);
        stage.setTitle("menu");
    }


    public void setUpVboxPaneLayout(VBox box)
    {
        box.setSpacing(spacing * 2);
        box.setAlignment(Pos.CENTER);
        box.setFillWidth(false);
        box.setPadding(new Insets(spacing * 2, spacing, spacing * 2, spacing));
        box.setStyle("-fx-border-color: black");
    }

    public static Stage create(Stage stage)
    {
        menu = new MenuView(stage);
        return stage;
    }

    public static void show()
    {
        menu.stage.show();
    }

    public static void close()
    {
        menu.stage.close();
    }
}
