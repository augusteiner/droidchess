/**
 *
 */
package android.chess.visao;

import android.annotation.TargetApi;
import android.chess.Main;
import android.chess.controle.PartidaControle;
import android.chess.dominio.interfaces.IPeca;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout.LayoutParams;

/**
 * @author augusteiner
 *
 */
public class Tabuleiro extends View {

    // private boolean moving;
    private Paint paint;
    private Peca peca;
    private Rect placeRect;
    private PartidaControle controle;

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
    private DisplayMetrics getDisplayMetrics() {
        return Main.getDisplayMetrics(getContext());
    }

    /**
     * @return
     */
    public Mensageiro getMensageiro() {
        return new Mensageiro(getContext());
    }

    /**
     * @return
     */
    public final int getSquareSide() {
        return (getMeasuredWidth() / 8);
    }

    /**
     * Prepara objetos para desenho deste tabuleiro.
     */
    protected void initTabuleiro() {
        controle = new PartidaControle();

        placeRect = new Rect();
        paint = new Paint();

        setBackgroundColor(Color.WHITE);
    }

    /*
     * (non-Javadoc)
     *
     * @see android.view.View#onDragEvent(android.view.DragEvent)
     */
    @TargetApi(11)
    @Override
    public boolean onDragEvent(DragEvent event) {
        Peca peca = (Peca) event.getLocalState();

        switch (event.getAction()) {
            case DragEvent.ACTION_DROP :
                if (peca != null) {
                    int destX = (int) (event.getX() / getSquareSide());
                    int destY = (int) (event.getY() / getSquareSide());

                    controle.jogada((IPeca)peca.getTag(), destX, destY);

                    performDrag(event.getX(), event.getY(), peca);

                    peca.setVisibility(VISIBLE);
                    peca.invalidate();

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

        paint.setColor(Color.BLACK);
        paint.setStyle(Style.FILL);

        for (int i = 0, j; i < 8; i++) {
            for (j = i % 2; j < 8; j += 2) {
                placeRect.set(i * tPlaceSide,// left,
                        j * tPlaceSide,// top,
                        (i + 1) * tPlaceSide,// right,
                        (j + 1) * tPlaceSide// bottom
                );

                // if (moving && i == 3) {
                // canvas.drawRect(tPlaceRect, tPaint);
                //
                // int color = tPaint.getColor();
                // float strokeWidth = tPaint.getStrokeWidth();
                //
                // tPaint.setColor(Color.parseColor("#661696C9"));
                // tPaint.setStrokeWidth(5.0F);
                //
                // canvas.drawRect(tPlaceRect, tPaint);
                //
                // tPaint.setColor(color);
                // tPaint.setStrokeWidth(strokeWidth);
                // } else {
                canvas.drawRect(placeRect, paint);
                // }
            }
        }

        placeRect.set(0, 0, width + 1, width + 1);
        paint.setStyle(Style.STROKE);
        canvas.drawRect(placeRect, paint);
    }

    /*
     * (non-Javadoc)
     *
     * @see android.view.View#onMeasure(int, int)
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!isInEditMode()) {
            int side = getDisplayMetrics().widthPixels;

            setMeasuredDimension(side, side);
        } else {
            setMeasuredDimension(480, 480);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see android.view.View#onTouchEvent(android.view.MotionEvent)
     */
    public boolean onTouchEvent(MotionEvent event, Peca peca) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                this.peca = peca;

                break;
            case MotionEvent.ACTION_UP :
                this.peca = null;

                break;
            case MotionEvent.ACTION_MOVE :
                performDrag(event.getX(), event.getY(), this.peca);

                break;
            default :
                return true;
        }
        return true;
    }

    /**
     * @param eventX
     * @param eventY
     * @param peca
     * @return
     */
    private boolean performDrag(float eventX, float eventY, Peca peca) {

        // p.getLayoutParams() utilizado para não perder a
        // referência
        // de alinhamento com a view do tabuleiro
        LayoutParams lp = (LayoutParams) peca.getLayoutParams();

        if (lp == null)
            return false;

        int x = (int) (eventX / peca.getWidth());
        int y = (int) (eventY / peca.getHeight());

        int leftMargin = x * peca.getWidth();
        int topMargin = y * peca.getHeight();

        lp.leftMargin = leftMargin;
        lp.topMargin = topMargin;

        // p.setTranslationX(x);
        // p.setTranslationY(y);

        peca.setLayoutParams(lp);
        peca.invalidate();

        return true;
    }
}