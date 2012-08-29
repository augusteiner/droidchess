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
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.DragEvent;
import android.view.ViewGroup;
import android.widget.TableLayout;

/**
 * @author augusteiner
 *
 */
public class Tabuleiro extends TableLayout {

    private Rect tPlaceRect;
    private Paint tPaint;
    private int tPlaceSide;
    private DisplayMetrics tDM;

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
//    public Tabuleiro(Context context, AttributeSet attrs, int defStyle) {
//        super(context, attrs, defStyle);
//
//        initTabuleiro();
//    }

    /**
     * Prepara objetos para desenho deste tabuleiro.
     */
    protected void initTabuleiro() {
        tPlaceRect = new Rect();
        tPaint = new Paint();

        tDM = getContext().getResources().getDisplayMetrics();
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

    /*
     * (non-Javadoc)
     *
     * @see android.view.View#onDragEvent(android.view.DragEvent)
     */
    @Override
    public boolean onDragEvent(DragEvent event) {
        Peca p = (Peca) event.getLocalState();

        switch (event.getAction()) {
            case DragEvent.ACTION_DROP :
                if (p != null) {
                    float x = (int) (event.getX() / p.getWidth())
                            * p.getWidth();
                    float y = (int) (event.getY() / p.getHeight())
                            * p.getHeight();

                    p.setLayoutParams(new ViewGroup.LayoutParams(p.getWidth(),
                            p.getHeight()));

                    p.setTranslationX(x);
                    p.setTranslationY(y);

                    p.invalidate();

                    return true;
                }

                return false;
            case DragEvent.ACTION_DRAG_ENDED :
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

        tPlaceSide = (width / 8);
        tPaint.setColor(Color.BLACK);
        tPaint.setStyle(Style.FILL);

        for (int i = 0, j; i < 8; i++) {
            for (j = i % 2; j < 8; j += 2) {
                tPlaceRect.set(i * tPlaceSide,// left,
                        j * tPlaceSide,// top,
                        (i + 1) * tPlaceSide,// right,
                        (j + 1) * tPlaceSide// bottom
                );

                canvas.drawRect(tPlaceRect, tPaint);
            }
        }

        tPlaceRect.set(0, 0, width + 1, width + 1);
        tPaint.setStyle(Style.STROKE);
        canvas.drawRect(tPlaceRect, tPaint);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // TODO Auto-generated method stub
        super.onLayout(changed, l, t, r, b);
    }
}