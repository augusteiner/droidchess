/**
 *
 */
package android.chess.client;

import android.content.ClipData;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * @author augusteiner
 *
 */
public class Peca extends android.chess.visao.Peca {

    private static final String TAG = Peca.class.getName();

    /**
     * @param context
     */
    public Peca(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param context
     * @param attrs
     */
    public Peca(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public Peca(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     *
     * @see android.chess.visao.Peca#getCoordI()
     */
    @Override
    public int getCoordI() {
        return (int) (getX() / getSide());
    }

    /*
     * (non-Javadoc)
     *
     * @see android.chess.visao.Peca#getCoordJ()
     */
    @Override
    public int getCoordJ() {
        return (int) (getY() / getSide());
    }

    /*
     * (non-Javadoc)
     *
     * @see android.chess.visao.Peca#onTouchEvent(android.view.MotionEvent)
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN :

                startDrag();

                return true;
            case MotionEvent.ACTION_UP :

                return true;
            case MotionEvent.ACTION_CANCEL :

                return true;
            default :
                return super.onTouchEvent(event);
        }
    }

    /**
     * @return
     */
    private boolean startDrag() {
        ClipData data = ClipData.newPlainText("Peca", "Move");
        DragShadowBuilder shadowBuilder = new DragShadowBuilder(this);
        Object myLocalState = this;
        int flags = 0;

        Log.d(TAG, String.format("Iniciando drag na peça %s.", getTag()));
        return startDrag(data, shadowBuilder, myLocalState, flags);
    }

}