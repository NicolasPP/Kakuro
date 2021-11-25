package kakuro.View.Cell;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;


public class SumCell
{
    public Canvas sumCanvas;
    int size;
    String columnSum;
    String rowSum;


    public SumCell(int size , String rowSum, String columnSum)
    {
        this.size = size;
        this.columnSum = columnSum;
        this.rowSum = rowSum;
        this.sumCanvas = setUpCanvas();
    }

    private Canvas setUpCanvas()
    {
        Canvas sumCell = new Canvas();
        sumCell.setWidth(size);
        sumCell.setHeight(size);
        drawLine(sumCell);
        drawColumnNumber(sumCell);
        drawRowNumber(sumCell);
        return sumCell;
    }

    private void drawLine(Canvas canv)
    {
        GraphicsContext draw = canv.getGraphicsContext2D();
        draw.clearRect(0,0,canv.getWidth() , canv.getHeight());
        draw.strokeLine(0,0,canv.getWidth(), canv.getHeight());
    }

    private void drawColumnNumber(Canvas canv)
    {
        if(!columnSum.equals("x"))
        {
            GraphicsContext draw = canv.getGraphicsContext2D();
            draw.fillText(
                    this.columnSum,
                    (this.size / 2) + 2,
                    (this.size / 2)
            );
        }
    }

    private void drawRowNumber(Canvas canv)
    {
        if(!rowSum.equals("x"))
        {
            GraphicsContext draw = canv.getGraphicsContext2D();
            draw.fillText(
                    this.rowSum,
                    (this.size / 2) -14,
                    (this.size / 2) + 9
            );
        }
    }
}
