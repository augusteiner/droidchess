package eng.android.chess.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

/**
 * Representa graficamente uma peça de um tabuleiro.
 *
 * @author augusteiner
 *
 */
public class Peca extends View
{

    private Tabuleiro tabuleiro;
    private Paint pPaint;

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public Peca(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);

        initPeca();
    }

    /**
     * @param context
     * @param attrs
     */
    public Peca(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        initPeca();
    }

    /**
     * @param context
     */
    public Peca(Context context)
    {
        super(context);

        initPeca();
    }

    /**
     * @param tabuleiro
     */
    public void setTabuleiro(Tabuleiro tabuleiro)
    {
        this.tabuleiro = tabuleiro;
    }

    /**
     *
     */
    private void initPeca()
    {
        pPaint = new Paint();

        setOnTouchListener(new eng.android.chess.views.listeners.OnTouchListener());
    }

    /*
     * (non-Javadoc)
     *
     * @see android.view.View#onDraw(android.graphics.Canvas)
     */
    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        pPaint.setColor(Color.LTGRAY);
        pPaint.setStyle(Style.FILL);

        int tPlaceSide = 60;// tabuleiro.getPlaceSide();
        canvas.drawCircle(
            tPlaceSide / 2,
            tPlaceSide / 2,
            tPlaceSide / 2 - 4,
            pPaint
        );
    }

    /*
     * (non-Javadoc)
     *
     * @see android.view.View#onMeasure(int, int)
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        setMeasuredDimension(getLayoutParams().width, getLayoutParams().height);
    }
}