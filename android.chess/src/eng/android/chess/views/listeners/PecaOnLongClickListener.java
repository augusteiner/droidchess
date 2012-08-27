package eng.android.chess.views.listeners;

import android.content.ClipData;
import android.view.View;
import android.view.View.OnLongClickListener;

public class PecaOnLongClickListener implements OnLongClickListener {

    /* (non-Javadoc)
     * @see android.view.View.OnLongClickListener#onLongClick(android.view.View)
     */
    @Override
    public boolean onLongClick(View v)
    {
        ClipData dragData = ClipData.newPlainText("label", "text");

        return v.startDrag(dragData, new View.DragShadowBuilder(v), null, 0);
    }

}
