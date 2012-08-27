package eng.android.chess.views.listeners;

import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;

public class PecaOnDragListener implements OnDragListener {

    /* (non-Javadoc)
     * @see android.view.View.OnDragListener#onDrag(android.view.View, android.view.DragEvent)
     */
    @Override
    public boolean onDrag(View v, DragEvent event) {
        switch (event.getAction())
        {
            case DragEvent.ACTION_DRAG_STARTED:
                return true;
            case DragEvent.ACTION_DROP:
                return true;
            case DragEvent.ACTION_DRAG_ENDED:
                v.invalidate();
                return true;
            default:
                return true;
        }
    }

}
