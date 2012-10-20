/**
 *
 */
package android.chess.app8;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout.LayoutParams;

/**
 * @author augusteiner
 *
 */
public class Peca extends android.chess.visao.PecaAbstrata {

    private Paint paint;
    private RectF rect;
    private PathEffect dash;

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
        dash = new DashPathEffect(new float[]{
            8, 4
        }, 10F);

        rect = new RectF(0, 0 + 1, getSide() - 1, getSide() - 1);

        setFocusableInTouchMode(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isFocused()) {
            paint.setColor(Color.argb(200, 0, 0, 255));
            paint.setStyle(Style.STROKE);
            paint.setPathEffect(dash);
            paint.setStrokeWidth(5);

            canvas.drawRect(rect, paint);
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
