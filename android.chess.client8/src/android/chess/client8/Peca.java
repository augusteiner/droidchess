/**
 *
 */
package android.chess.client8;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout.LayoutParams;

/**
 * @author augusteiner
 *
 */
public class Peca extends android.chess.visao.PecaAbstrata {

    private Paint paint;

    /**
     * @param context
     */
    public Peca(Context context) {
        super(context);

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
     * @param attrs
     * @param defStyle
     */
    public Peca(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        initPeca();
    }

    /**
     * @return
     */
    @Override
    public LayoutParams getLayoutParams() {
        return (LayoutParams) super.getLayoutParams();
    }

    /**
     *
     */
    private void initPeca() {
        paint = new Paint();

        setFocusableInTouchMode(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isFocused()) {
            paint.setColor(Color.argb(200, 50, 50, 205));
            paint.setStyle(Style.STROKE);
            paint.setStrokeWidth(10);

            canvas.drawRect(0, 0, getSide(), getSide(), paint);
        }

        super.onDraw(canvas);
    }

    /*
     * (non-Javadoc)
     *
     * @see android.view.View#onFocusChanged(boolean, int,
     * android.graphics.Rect)
     */
    @Override
    protected void onFocusChanged(boolean gainFocus, int direction,
        Rect previouslyFocusedRect) {
        invalidate();

        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
    }

    /*
     * (non-Javadoc)
     *
     * @see android.view.View#onTouchEvent(android.view.MotionEvent)
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                return requestFocus();
            case MotionEvent.ACTION_CANCEL :
                return true;
            case MotionEvent.ACTION_UP :
                return true;
            default :
                return super.onTouchEvent(event);
        }
    }

}
