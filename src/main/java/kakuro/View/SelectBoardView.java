package kakuro.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import kakuro.Controller.SelectBoardViewController;
import kakuro.Model.Board;
import kakuro.util.Resources;

import java.util.List;

public class SelectBoardView
{
    public static SelectBoardView selectBoardPage;
    Stage stage;
    Scene window;
    AnchorPane anchor;
    VBox page;
    List<Board> boards;
    ListView boardListView;
    Button goBack;
    Text title;
    String dimension;
    List<Board> boardList;
    final String GO_BACK = "goBack.png";
    final private String PATH = "C:\\Users\\nicol\\Documents\\Projects\\Kakuro\\src\\main\\resources\\";
    final private int imgSize = 40;
    final private int spacing = 10;
    final int sceneHeight = 400;
    final int sceneWidth = 250;

    public SelectBoardView(List<Board> boardList, String dimension, List<Board> boards)
    {
        this.boards = boards;
        this.boardList = boardList;
        this.dimension = dimension;
        this.title = setUpText();
        this.goBack = setUpButton();
        this.boardListView = setUpBoardListView();
        this.page = setUpPage();
        this.anchor = setUpAnchor();
        this.window = setUpScene();
        this.stage = setUpStage();
    }

    private Button setUpButton()
    {
        Button btn = new Button();
        btn.setPrefHeight(imgSize);
        btn.setPrefWidth(imgSize);
        addButtonImage(btn);
        return btn;
    }

    private ListView setUpBoardListView()
    {
        ListView bList = new ListView();

        for (int i = 0; i < boardList.size(); i++)
        {
            Text text = new Text("#" + (i + 1));
            text.setId(i+"");
            text.setTextAlignment(TextAlignment.RIGHT);
            bList.getItems().add(text);
        }

        return bList;
    }

    private VBox setUpPage() {
        VBox vb = new VBox();
        setUpVboxPaneLayout(vb);

        vb.getChildren().addAll
                (
                        this.title,
                        this.boardListView,
                        this.goBack
                );

        return vb;
    }

    private AnchorPane setUpAnchor()
    {
        AnchorPane ac = new AnchorPane();

        AnchorPane.setRightAnchor(page , (double) spacing);
        AnchorPane.setLeftAnchor(page , (double) spacing);
        AnchorPane.setTopAnchor(page , (double) spacing);
        AnchorPane.setBottomAnchor(page , (double) spacing);

        ac.getChildren().add(page);
        return ac;
    }

    private Scene setUpScene()
    {
        new SelectBoardViewController
                (
                        this.boardListView,
                        this.goBack,
                        this.dimension,
                        this.boards

                ).addControllers();
        return new Scene(this.anchor, sceneWidth, sceneHeight);
    }

    private Stage setUpStage()
    {
        Stage st = new Stage();
        st.setScene(window);
        st.setTitle("Pick Board");
        st.setResizable(false);
        return st;
    }

    private Text setUpText()
    {
        String test = this.dimension.split(":")[0];
        Text textTitle = new Text(test);
        textTitle.setFont(Font.font("Arial Italic" , 20));
        return textTitle;
    }

    private void addButtonImage(Button btn)
    {
        Image img = new Image(Resources.getIconPath(GO_BACK));
        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(imgSize);
        imgView.setFitWidth(imgSize);
        imgView.setPreserveRatio(false);
        btn.setGraphic(imgView);
    }

    public void setUpVboxPaneLayout(VBox box)
    {
        box.setSpacing(spacing * 2);
        box.setAlignment(Pos.CENTER);
        box.setFillWidth(false);
        box.setPadding(new Insets(spacing * 2, spacing, spacing * 2, spacing));
        box.setStyle("-fx-border-color: black");
    }

    public static void show()
    {
        selectBoardPage.stage.show();
    }

    public static void close()
    {
        selectBoardPage.stage.close();
    }

    public static void create(List<Board> boardList, String dimension, List<Board> boards)
    {
        selectBoardPage = new SelectBoardView(boardList,dimension, boards);
    }

}
