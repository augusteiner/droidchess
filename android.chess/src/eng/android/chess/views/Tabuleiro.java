/**
 *
 */
package eng.android.chess.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout.LayoutParams;

/**
 * @author augusteiner
 *
 */
public class Tabuleiro extends View {

    private Rect tPlaceRect;
    private Paint tPaint;
    private DisplayMetrics tDM;
    private boolean moving;

    /**
     * @param context
     */
    public Tabuleiro(Context context) {
        super(context);

        initTabuleiro();
    }

    /**
     * @param context
     * @param attrs
     */
    public Tabuleiro(Context context, AttributeSet attrs) {
        super(context, attrs);

        initTabuleiro();
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public Tabuleiro(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        initTabuleiro();
    }

    /**
     * @return
     */
    public int getSquareSide() {
        return (getMeasuredWidth() / 8);
    }

    /**
     * Prepara objetos para desenho deste tabuleiro.
     */
    protected void initTabuleiro() {
        tPlaceRect = new Rect();
        tPaint = new Paint();
        // partida = new Partida();

        setBackgroundColor(Color.WHITE);

        Context context = getContext();

        tDM = context.getResources().getDisplayMetrics();

        // Peca peca = null;
        //
        // int width = getSquareSide();
        //
        // for (int i = 0, j; i < 1; i++) {
        // for (j = 0; j < 1; j++) {
        // peca = new Peca(context);
        // LayoutParams lp = new LayoutParams(width, width);
        //
        // lp.leftMargin = width * j;
        // lp.topMargin = width * i;
        //
        // //((ViewGroup) getContext().get).addView(peca, lp);
        // //
        // }
        // }
    }

    /*
     * (non-Javadoc)
     *
     * @see android.view.View#onDragEvent(android.view.DragEvent)
     */
    @Override
    public boolean onDragEvent(DragEvent event) {
        Peca p = (Peca) event.getLocalState();

        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_ENTERED:
                moving = true;
                invalidate();
            case DragEvent.ACTION_DROP :
                moving = false;

                if (p != null) {
                    int x = (int) (event.getX() / p.getWidth());
                    int y = (int) (event.getY() / p.getHeight());

                    int leftMargin = x * p.getWidth();
                    int topMargin = y * p.getHeight();

                    // p.getLayoutParams() utilizado para não perder a
                    // referência
                    // de alinhamento com a view do tabuleiro
                    LayoutParams lp = (LayoutParams) p.getLayoutParams();

                    if (lp != null) {
                        lp.leftMargin = leftMargin;
                        lp.topMargin = topMargin;

                        // p.setTranslationX(x);
                        // p.setTranslationY(y);

                        p.setLayoutParams(lp);
                    }

                    p.invalidate();

                    return true;
                }

                return false;
            case DragEvent.ACTION_DRAG_ENDED :
                invalidate();
                return true;
            default :
                return true;
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see android.view.View#onDraw(android.graphics.Canvas)
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getMeasuredWidth();
        int tPlaceSide = getSquareSide();

        tPaint.setColor(Color.BLACK);
        tPaint.setStyle(Style.FILL);

        for (int i = 0, j; i < 8; i++) {
            for (j = i % 2; j < 8; j += 2) {
                tPlaceRect.set(i * tPlaceSide,// left,
                        j * tPlaceSide,// top,
                        (i + 1) * tPlaceSide,// right,
                        (j + 1) * tPlaceSide// bottom
                );

                if (moving && i == 3) {
                    canvas.drawRect(tPlaceRect, tPaint);

                    int color = tPaint.getColor();
                    float strokeWidth = tPaint.getStrokeWidth();

                    tPaint.setColor(Color.parseColor("#661696C9"));
                    tPaint.setStrokeWidth(5.0F);

                    canvas.drawRect(tPlaceRect, tPaint);

                    tPaint.setColor(color);
                    tPaint.setStrokeWidth(strokeWidth);
                } else {
                    canvas.drawRect(tPlaceRect, tPaint);
                }
            }
        }

        tPlaceRect.set(0, 0, width + 1, width + 1);
        tPaint.setStyle(Style.STROKE);
        canvas.drawRect(tPlaceRect, tPaint);
    }

    /*
     * (non-Javadoc)
     *
     * @see android.view.View#onMeasure(int, int)
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!isInEditMode())
            setMeasuredDimension(tDM.widthPixels, tDM.widthPixels);
        else
            setMeasuredDimension(480, 480);
    }
}