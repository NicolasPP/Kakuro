package kakuro.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import kakuro.Controller.WinViewController;
import kakuro.Model.Board;
import kakuro.View.Cell.NumberCell;
import kakuro.util.Resources;

import java.util.List;

public class WinView
{
    static WinView winPage;
    Stage stage;
    Scene window;
    AnchorPane anchor;
    VBox page;
    Text winMessage;
    Button goBack;
    final int nodeSize = 40;
    final int spacing = 10;
//    List<NumberCell> cellList;
    final String GO_BACK = "goBack.png";

    public WinView()
    {
        this.winMessage = setUpText();
        this.goBack = setUpButton();
        this.page = setUpPage();
        this.anchor = setUpAnchor();
        this.window = setUpWindow();
        this.stage = setUpStage();
    }

    private Text setUpText()
    {
        Text txt = new Text("Completed !");
        txt.setFont(Font.font(20));
        return txt;
    }

    private Button setUpButton()
    {
        Button btn = new Button();
        btn.setPrefHeight(nodeSize);
        btn.setPrefWidth(nodeSize);
        addButtonImage(Resources.getIconPath(GO_BACK),btn);
        return btn;
    }

    private VBox setUpPage()
    {
        VBox vbox = new VBox();

        setUpVboxPaneLayout(vbox);

        vbox.getChildren().addAll
                (
                        this.winMessage,
                        this.goBack
                );


        return vbox;
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
        st.setTitle("congrats!");
        st.setResizable(false);

        new WinViewController(goBack).addControllers();
        return st;
    }

    private void addButtonImage(String fileName, Button btn)
    {
        Image img = new Image(fileName);
        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(nodeSize);
        imgView.setFitWidth(nodeSize);
        imgView.setPreserveRatio(false);
        btn.setGraphic(imgView);
    }

    private void setUpVboxPaneLayout(VBox box)
    {
        box.setSpacing(spacing * 2);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(spacing * 2, spacing * 2, spacing * 2, spacing * 2));
        box.setStyle("-fx-border-color: black");
    }

    public static void show()
    {
        winPage.stage.show();
    }

    public static void close()
    {
        winPage.stage.close();
    }

    public static void create()
    {
        winPage = new WinView();
    }
}
