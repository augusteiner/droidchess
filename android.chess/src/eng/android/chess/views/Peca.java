package eng.android.chess.views;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

/**
 * Representa graficamente uma peça de um tabuleiro.
 *
 * @author augusteiner
 *
 */
public class Peca extends View {

    private Paint pPaint;
    private DisplayMetrics pDM;

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public Peca(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        initPeca();
    }

    /**
     * @param context
     * @param attrs
     */
    public Peca(Context context, AttributeSet attrs) {
        super(context, attrs);

        initPeca();
    }

    /**
     * @param context
     */
    public Peca(Context context) {
        super(context);

        initPeca();
    }

    /**
     *
     */
    private void initPeca() {
        pPaint = new Paint();

        pDM = getContext().getResources().getDisplayMetrics();
    }

    /*
     * (non-Javadoc)
     *
     * @see android.view.View#onTouchEvent(android.view.MotionEvent)
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN :

                ClipData data = ClipData.newPlainText("Peca", "Move");
                DragShadowBuilder shadowBuilder = new DragShadowBuilder(this);
                Object myLocalState = this;
                int flags = 0;

                setVisibility(View.INVISIBLE);

                startDrag(data, shadowBuilder, myLocalState, flags);

                return true;
            case MotionEvent.ACTION_UP :
                return true;
            case MotionEvent.ACTION_CANCEL :
                setVisibility(View.VISIBLE);

                return true;
            default :
                return super.onTouchEvent(event);
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

        pPaint.setColor(Color.LTGRAY);
        pPaint.setStyle(Style.FILL);

        int width = getMeasuredWidth();

        canvas.drawCircle(width / 2, width / 2, width / 2 - 4, pPaint);
    }

    /*
     * (non-Javadoc)
     *
     * @see android.view.View#onMeasure(int, int)
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(pDM.widthPixels / 8, pDM.widthPixels / 8);
    }
}