package kakuro.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import kakuro.Controller.DifficultyViewController;
import kakuro.util.Resources;

public class DifficultyView
{
    static DifficultyView difficultyPage;
    public Stage stage;
    Scene window;
    AnchorPane anchor;
    VBox page;
    Button easy;
    Button medium;
    Button difficult;
    Button goBack;
    final private int buttonHeight = 30;
    final private int buttonWidth = 80;
    final private int imgSize = 40;
    final private int spacing = 10;
    final private String PATH = "C:\\Users\\nicol\\Documents\\Projects\\Kakuro\\src\\main\\resources\\";
    final private String EASY = "EASY";
    final private String MEDIUM = "MEDIUM";
    final private String DIFFICULT = "HARD";
    final String GO_BACK = "goBack.png";
    final int sceneHeight = 400;
    final int sceneWidth = 250;

    public DifficultyView()
    {
        this.easy = setUpButton(EASY, buttonHeight, buttonWidth);
        this.medium = setUpButton(MEDIUM, buttonHeight, buttonWidth);
        this.difficult = setUpButton(DIFFICULT, buttonHeight, buttonWidth);
        this.goBack = setUpButton("", imgSize, imgSize);
        this.page = setUpPage();
        this.anchor = setUpAnchor();
        this.window = setUpWindow();
        this.stage = setUpStage();
    }

    private Button setUpButton(String name, int height, int width)
    {
        Button btn = new Button(name);
        btn.setPrefHeight(height);
        btn.setPrefWidth(width);
        return btn;
    }

    private VBox setUpPage()
    {
        VBox p = new VBox();
        setUpVboxPaneLayout(p);

        addButtonImage();
        p.getChildren().addAll
                (
                this.easy,
                        this.medium,
                        this.difficult,
                        this.goBack
        );

        return p;
    }

    private AnchorPane setUpAnchor()
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

    private Scene setUpWindow()
    {
        return new Scene(this.anchor, sceneWidth, sceneHeight);
    }

    private Stage setUpStage()
    {
        new DifficultyViewController
                (
                        this.easy,
                        this.medium,
                        this.difficult,
                        this.goBack
                ).addControllers();


        Stage st = new Stage();
        st.setScene(this.window);
        st.setTitle("Pick DIfficulty");
        st.setResizable(false);

        return st;
    }

    private void setUpVboxPaneLayout(VBox box)
    {
        box.setSpacing(spacing * 2);
        box.setAlignment(Pos.CENTER);
        box.setFillWidth(false);
        box.setPadding(new Insets(spacing * 6, spacing * 8, spacing * 6, spacing * 8));
        box.setStyle("-fx-border-color: black");
    }

    private void addButtonImage()
    {
        Image img = new Image(Resources.getIconPath(GO_BACK));
        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(imgSize);
        imgView.setFitWidth(imgSize);
        imgView.setPreserveRatio(false);
        goBack.setGraphic(imgView);
    }

    public static void create()
    {
        difficultyPage = new DifficultyView();
    }

    public static void show()
    {
        difficultyPage.stage.show();
    }

    public static void close()
    {
        difficultyPage.stage.close();
    }

}
