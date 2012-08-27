/**
 *
 */
package eng.android.chess.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.View;

/**
 * @author augusteiner
 *
 */
public class Tabuleiro extends View {

	private Rect tPlaceRect;
	private Paint tPaint;
	private int tPlaceSide;

	/**
	 * @param context
	 */
	public Tabuleiro(Context context) {
		super(context);

		initTabuleiro();
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public Tabuleiro(Context context, AttributeSet attrs) {
		super(context, attrs);

		initTabuleiro();
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public Tabuleiro(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		initTabuleiro();
	}

	/**
	 * Prepara objetos para desenho deste tabuleiro.
	 */
	protected void initTabuleiro() {
		tPlaceRect = new Rect();
		tPaint = new Paint();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see android.view.View#onMeasure(int, int)
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(getLayoutParams().width + 1,
				getLayoutParams().height + 2);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see android.view.View#onDragEvent(android.view.DragEvent)
	 */
	@Override
	public boolean onDragEvent(DragEvent event) {
		switch (event.getAction()) {
			case DragEvent.ACTION_DROP :
				Peca p = (Peca)event.getLocalState();

				if (p != null){
					//

					return true;
				}

				break;
			default :
				return false;
		}

		// TODO Auto-generated method stub
		return super.onDragEvent(event);
	}
	/*
	 * (non-Javadoc)
	 *
	 * @see android.view.View#onDraw(android.graphics.Canvas)
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		tPlaceSide = getLayoutParams().width / 8;
		tPaint.setColor(Color.BLACK);
		tPaint.setStyle(Style.FILL);

		for (int i = 0, j; i < 8; i++) {
			for (j = i % 2; j < 8; j += 2) {
				tPlaceRect.set(i * tPlaceSide,// left,
						j * tPlaceSide,// top,
						(i + 1) * tPlaceSide,// right,
						(j + 1) * tPlaceSide// bottom
				);

				canvas.drawRect(tPlaceRect, tPaint);
			}
		}

		tPlaceRect.set(0, 0, getLayoutParams().width, getLayoutParams().height);
		tPaint.setStyle(Style.STROKE);
		canvas.drawRect(tPlaceRect, tPaint);
	}

	/**
	 * Retorna o tamanho de uma posição do tabuleiro.
	 *
	 * @return
	 */
	public int getPlaceSide() {
		return tPlaceSide;
	}
}
