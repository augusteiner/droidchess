package eng.android.chess.views.listeners;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * @author augusteiner
 *
 */
public class OnTouchListener implements View.OnTouchListener {
	/**
     *
     */
	private boolean isDown;
	private boolean moved;

	/**
     *
     */
	public OnTouchListener() {
		isDown = false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see android.view.View.OnTouchListener#onTouch(android.view.View,
	 * android.view.MotionEvent)
	 */
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN :
				isDown = true;

				return true;
			case MotionEvent.ACTION_UP :
				isDown = false;

				if (moved) {
					moved = false;

					LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
							new ViewGroup.MarginLayoutParams(
									LinearLayout.LayoutParams.WRAP_CONTENT,
									LinearLayout.LayoutParams.WRAP_CONTENT));
					lp.setMargins((int)event.getX(), (int)event.getY(), 0, 0);

					v.setLayoutParams(lp);

					v.setVisibility(View.VISIBLE);
					v.invalidate();

					return true;
				}

				return false;
			case MotionEvent.ACTION_MOVE :
				if (isDown) {
					moved = true;

					v.setVisibility(View.INVISIBLE);
					v.invalidate();

					return true;
				} else {
					return false;
				}
			default :
				return false;
		}
	}

}
