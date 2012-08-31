package eng.android.chess.views;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import eng.android.chess.R;

/**
 * Representa graficamente uma peça de um tabuleiro.
 *
 * @author augusteiner
 *
 */
public class Peca extends View {

    private static final String TAG = View.class.getSimpleName();
    private Paint pPaint;
    private DisplayMetrics pDM;

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
     *
     */
    private void initPeca() {
        pPaint = new Paint();

        pDM = getContext().getResources().getDisplayMetrics();
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

    /*
     * (non-Javadoc)
     *
     * @see android.view.View#onTouchEvent(android.view.MotionEvent)
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (Build.VERSION.SDK_INT > 11) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN :
                    startDrag();

                    return true;
                case MotionEvent.ACTION_UP :
                    return true;
                case MotionEvent.ACTION_CANCEL :
                    setVisibility(View.VISIBLE);

                    return true;
                default :
                    return super.onTouchEvent(event);
            }
        } else {
            return startDragOlder(event);
        }
    }
    /**
     * @return
     */
    @TargetApi(11)
    private boolean startDrag() {
        ClipData data = ClipData.newPlainText("Peca", "Move");
        DragShadowBuilder shadowBuilder = new DragShadowBuilder(this);
        Object myLocalState = this;
        int flags = 0;

        setVisibility(View.INVISIBLE);

        return startDrag(data, shadowBuilder, myLocalState, flags);
    }
    /**
     * @deprecated
     *
     * @return
     */
    @Deprecated
    private boolean startDragOlder(MotionEvent event) {
        View p = (View) getParent();
        if (p != null) {
            Tabuleiro tabuleiro = (Tabuleiro) p.findViewById(R.id.tabuleiro);

            if (tabuleiro != null) {
                Log.d(TAG,
                        String.format("startDragOlder : %d", event.getAction()));

                // event.get
                return tabuleiro.onTouchEvent(event, this);
            }
        }

        return super.onTouchEvent(event);
    }
}