package kakuro.View;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import kakuro.Controller.RestartViewController;



public class RestartView
{
    static RestartView restartPage;
    Stage stage;
    Scene window;
    AnchorPane anchor;
    VBox page;
    TilePane buttonPane;
    Text warningMessage;
    Button yes;
    Button no;
    final int nodeHeight = 30;
    final int nodeWidth = 55;
    final int spacing = 10;


    public RestartView()
    {
        this.warningMessage = setUpText();
        this.yes = setUpButton("YES");
        this.no = setUpButton("NO");
        this.buttonPane = setUpButtonPane();
        this.page = setUpPage();
        this.anchor = setUpAnchor();
        this.window = setUpWindow();
        this.stage = setUpStage();
    }

    private Text setUpText()
    {
        Text txt = new Text("Restart the game?");
        txt.setFont(Font.font(10));
        return txt;
    }

    private Button setUpButton(String name)
    {
        Button btn = new Button(name);
        btn.setId(name);
        btn.setPrefHeight(nodeHeight);
        btn.setPrefWidth(nodeWidth  );
        return btn;
    }

    private VBox setUpPage()
    {
        VBox vbox = new VBox();

        setUpVboxPaneLayout(vbox);

        vbox.getChildren().addAll
                (
                        this.warningMessage,
                        this.buttonPane
                );


        return vbox;
    }

    private TilePane setUpButtonPane()
    {
        TilePane btnPane = new TilePane();

        setUpTilePaneLayout(btnPane,1,2);

        btnPane.getChildren().addAll
                (
                        this.yes,
                        this.no
                );

        return btnPane;
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
        return new Scene(
                this.anchor
        );
    }

    private Stage setUpStage()
    {
        Stage st = new Stage();
        st.setScene(window);
        st.setTitle("restart!");
        st.setResizable(false);

        new RestartViewController
                (
                        this.yes,
                        this.no
                ).addControllers();

        return st;
    }

    private void setUpTilePaneLayout(TilePane grid , int rowNum , int columnNum)
    {
        grid.setVgap(spacing);
        grid.setHgap(spacing);
        grid.setPadding(new Insets(spacing, spacing, spacing, spacing));
        grid.setPrefRows(rowNum);
        grid.setPrefColumns(columnNum);
        grid.setStyle("-fx-border-color: black");
    }

    private void setUpVboxPaneLayout(VBox box)
    {
        box.setSpacing(spacing * 2);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(spacing * 2, spacing * 2, spacing * 2, spacing * 2));
    }

    public static void show()
    {
        restartPage.stage.show();
    }

    public static void close()
    {
        restartPage.stage.close();
    }

    public static void create()
    {
        restartPage = new RestartView();
    }
}
