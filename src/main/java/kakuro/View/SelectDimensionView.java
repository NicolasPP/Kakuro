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
import javafx.stage.Stage;
import kakuro.Controller.SelectDimensionViewController;
import kakuro.Model.Board;
import kakuro.Model.BoardData;
import kakuro.util.Resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectDimensionView
{
    static SelectDimensionView selectDimensionPage;
    Stage stage;
    Scene window;
    AnchorPane anchor;
    VBox page;
    Text title;
    ListView dimensionList;
    List<String> dimensions;
    BoardData boardData;
    String fileName;
    HashMap<String, List<Board>> dimensionBoard;
    Button goBack;
    String difficulty;
    final String GO_BACK = "goBack.png";
    final private int imgSize = 40;
    final int spacing = 10;
    final int sceneHeight = 400;
    final int sceneWidth = 250;

    public SelectDimensionView(String fileName)
    {
        this.difficulty = fileName;
        this.fileName = fileName;
        this.boardData = new BoardData();
        this.title = setUpText();
        this.dimensionBoard = boardData.getBoardsByDimensions(fileName);
        this.dimensions = setUpDimensions();
        this.dimensionList = setUpDimensionsList();
        this.goBack = setUpButton();
        this.page = setUpPage();
        this.anchor = setUpAnchor();
        this.window = setUpScene();
        this.stage = setUpStage();

    }


    private List<String> setUpDimensions()
    {
        List<String> dimList = new ArrayList<>();
        for(Map.Entry<String, List<kakuro.Model.Board>> entry : dimensionBoard.entrySet())
        {
            String key = entry.getKey();
            List<kakuro.Model.Board> bData =  entry.getValue();
            dimList.add(key + "  :  " + bData.size());
        }
        return dimList;
    }

    private ListView setUpDimensionsList()
    {
        ListView dimList = new ListView();

        for (String dim : dimensions)
        {
            dimList.setId(dim);
            dimList.getItems().add(dim);
        }
        return dimList;
    }

    private VBox setUpPage()
    {
        VBox vb = new VBox();
        setUpVboxPaneLayout(vb);

        addButtonImage();

        vb.getChildren().addAll
                (
                        this.title,
                        this.dimensionList,
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
        new SelectDimensionViewController
                (
                        this.dimensionList,
                        this.goBack,
                        this.difficulty,
                        this.boardData
                ).addControllers();
        return new Scene(this.anchor, sceneWidth , sceneHeight);
    }

    private Stage setUpStage()
    {
        Stage st = new Stage();
        st.setScene(window);
        st.setTitle("Pick Dimension");
        st.setResizable(false);
        return st;
    }

    private void setUpVboxPaneLayout(VBox box)
    {
        box.setSpacing(spacing * 2);
        box.setAlignment(Pos.CENTER);
        box.setFillWidth(false);
        box.setPadding(new Insets(spacing * 2, spacing, spacing * 2, spacing));
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

    private Button setUpButton()
    {
        Button btn = new Button("");
        btn.setPrefHeight(imgSize);
        btn.setPrefWidth(imgSize);
        return btn;
    }

    private Text setUpText()
    {
        String test = this.difficulty.substring(0,difficulty.length() - 9) + "s";
        Text textTitle = new Text(test);
        textTitle.setFont(Font.font("Arial Italic" , 20));
        return textTitle;
    }

    public static void show()
    {
        selectDimensionPage.stage.show();
    }

    public static void close()
    {
        selectDimensionPage.stage.close();
    }

    public static void create(String fileName)
    {
        selectDimensionPage = new SelectDimensionView(fileName);
    }
}
