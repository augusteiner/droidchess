/**
 * 
 */
package com.example.eng.android.teste.views;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author augusteiner
 * 
 */
public class Draggable extends View {

    private Paint dPaint;

    /**
     * @param context
     */
    public Draggable(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        initDraggable();
    }

    /**
     * @param context
     * @param attrs
     */
    public Draggable(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        initDraggable();
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public Draggable(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        initDraggable();
    }

    /**
     * 
     */
    private void initDraggable() {
        dPaint = new Paint();
        
        setOnTouchListener(new Draggable.OnTouchListener());
    }
    /*
     * (non-Javadoc)
     * 
     * @see android.view.View#onMeasure(int, int)
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(30, 30);
    }
    /*
     * (non-Javadoc)
     * 
     * @see android.view.View#onDraw(android.graphics.Canvas)
     */
    @Override
    protected void onDraw(Canvas canvas) {
        dPaint.setColor(Color.BLACK);
        canvas.drawCircle(15, 15, 15, dPaint);

        super.onDraw(canvas);
    }
    /* (non-Javadoc)
     * @see android.view.View#onDragEvent(android.view.DragEvent)
     */
    @Override
    public boolean onDragEvent(DragEvent event) {
        return true;
    }
    /**
     * @author augusteiner
     * 
     */
    class OnTouchListener implements android.view.View.OnTouchListener {
        /*
         * (non-Javadoc)
         * 
         * @see android.view.View.OnTouchListener#onTouch(android.view.View,
         * android.view.MotionEvent)
         */
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText(
                    "Draggable_Action_Down",
                    ""
                );
                DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                Object myLocalState = v;
                int flags = 0;

                v.startDrag(data, shadowBuilder, myLocalState, flags);
                
                return true;
            }

            // TODO Auto-generated method stub
            return false;
        }
    }
}
