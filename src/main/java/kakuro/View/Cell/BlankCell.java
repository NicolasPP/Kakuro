package kakuro.View.Cell;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class BlankCell
{
    int size;
    public Canvas blank;

    public BlankCell(int size)
    {
        this.size = size;
        this.blank = setUpCanvas();
    }

    public Canvas setUpCanvas()
    {
        Canvas can = new Canvas();
        can.setHeight(size);
        can.setWidth(size);
        draw(can);
        return can;
    }

    public void draw(Canvas canvas)
    {
        GraphicsContext draw = canvas.getGraphicsContext2D();
        draw.setFill(Color.BLACK);
        draw.fillRect(0,0,30,30);
    }
}
