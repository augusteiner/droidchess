/**
 *
 */
package com.example.eng.android.teste.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author augusteiner
 *
 */
public class DragLayer extends ViewGroup {

    /**
     * @param context
     */
    public DragLayer(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        initDragLayer();
    }

    /**
     * @param context
     * @param attrs
     */
    public DragLayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        initDragLayer();
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public DragLayer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        initDragLayer();
    }
    /**
     *
     */
    private void initDragLayer()
    {
        setOnDragListener(new DragLayer.OnDragListener());
    }

    /*
     * (non-Javadoc)
     *
     * @see android.view.ViewGroup#onLayout(boolean, int, int, int, int)
     */
    @Override
    protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) {
        // TODO Auto-generated method stub
    }
    /**
     * @author augusteiner
     *
     */
    class OnDragListener implements View.OnDragListener
    {
        /* (non-Javadoc)
         * @see android.view.View.OnDragListener#onDrag(android.view.View, android.view.DragEvent)
         */
        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
            case DragEvent.ACTION_DROP:
                Draggable d = (Draggable)event.getLocalState();
                d.invalidate();

                d.setX(event.getX() - d.getWidth() / 2);
                d.setY(event.getY() - d.getHeight() / 2);

                return true;
            case DragEvent.ACTION_DRAG_ENDED:
                return true;
            default:
                return true;
            }
        }

    }
}