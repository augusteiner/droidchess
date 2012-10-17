/**
 *
 */
package android.chess.visao;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static java.lang.Math.min;

import java.util.Iterator;

import android.annotation.TargetApi;
import android.chess.Main;
import android.chess.controle.PartidaControle;
import android.chess.dominio.excecao.ChessException;
import android.chess.dominio.excecao.JogadaException;
import android.chess.dominio.interfaces.IPeca;
import android.chess.dominio.interfaces.IPromocaoInfo;
import android.chess.dominio.interfaces.ITomadaInfo;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

/**
 * @author augusteiner
 *
 */
public class Tabuleiro extends View {

    private static final String TAG = Tabuleiro.class.getSimpleName();
    private PartidaControle controle;
    private Mensageiro mensageiro;
    // private boolean moving;
    private Paint paint;
    // TODO Reabilitar no caso da implementação de suporte a uma API antiga.
    // private Peca peca;
    private Rect placeRect;

    /**
     * @param context
     */
    public Tabuleiro(Context context) {
        super(context);

        init();
    }

    /**
     * @param context
     * @param attrs
     */
    public Tabuleiro(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public Tabuleiro(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init();
    }

    /**
     * @return
     */
    private DisplayMetrics getDisplayMetrics() {
        return Main.getDisplayMetrics(getContext());
    }

    /**
     * @return
     */
    public Mensageiro getMensageiro() {
        return new Mensageiro(getContext());
    }

    /**
     * @return
     */
    public final int getSquareSide() {
        return (getMeasuredWidth() / 8);
    }

    /**
     *
     */
    public void init() {
        mensageiro = new Mensageiro(getContext());
        controle = new PartidaControle();

        placeRect = new Rect();
        paint = new Paint();

        setBackgroundColor(Color.WHITE);
    }

    /**
     * Prepara objetos para desenho deste tabuleiro.
     */
    public void init(ViewGroup contentView) {
        controle.novaPartida();

        contentView.removeAllViews();
        contentView.addView(this);

        initPecas(contentView);
    }

    /**
     * @param contentView
     *
     */
    protected void initPecas(ViewGroup contentView) {
        Iterator<IPeca> pecas = controle.getTabuleiro().getPecas();
        IPeca next = null;
        Context context = getContext();

        Peca peca = null;

        LayoutParams lp = new LayoutParams(WRAP_CONTENT, WRAP_CONTENT);

        while (pecas.hasNext()) {
            peca = new Peca(context);
            // peca.setOnTomadaHandler(this);

            next = pecas.next();
            peca.setTag(next);
            peca.init();

            lp = new LayoutParams(lp);

            // LayoutParams não copia as "rules" definidas.
            lp.addRule(RelativeLayout.ALIGN_TOP, getId());

            lp.topMargin = peca.getSide() * next.getI();
            lp.leftMargin = peca.getSide() * next.getJ();

            contentView.addView(peca, lp);
        }
    }

    public void onAntesPromocao(Object sender, IPromocaoInfo evento)
        throws ChessException {
        //
    }

    public void onDepoisPromocao(Object sender, IPromocaoInfo evento)
        throws ChessException {
        //
    }

    /*
     * (non-Javadoc)
     *
     * @see android.view.View#onDragEvent(android.view.DragEvent)
     */
    @TargetApi(11)
    @Override
    public boolean onDragEvent(DragEvent event) {
        try {
            return performOnDragEvent(event);
            // } catch (ArrayIndexOutOfBoundsException e) {
            // e.printStackTrace();
            // return false;
        } catch (Exception e) {
            e.printStackTrace();

            mensageiro.alertar(e.getMessage());

            Log.d(TAG, e.toString());

            return false;
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see android.view.View#onDraw(android.graphics.Canvas)
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getMeasuredWidth();
        int tPlaceSide = getSquareSide();

        paint.setColor(Color.BLACK);
        paint.setStyle(Style.FILL);

        for (int i = 0, j; i < 8; i++) {
            for (j = i % 2; j < 8; j += 2) {
                placeRect.set(i * tPlaceSide,// left,
                    j * tPlaceSide,// top,
                    (i + 1) * tPlaceSide,// right,
                    (j + 1) * tPlaceSide// bottom
                );

                // if (moving && i == 3) {
                // canvas.drawRect(tPlaceRect, tPaint);
                //
                // int color = tPaint.getColor();
                // float strokeWidth = tPaint.getStrokeWidth();
                //
                // tPaint.setColor(Color.parseColor("#661696C9"));
                // tPaint.setStrokeWidth(5.0F);
                //
                // canvas.drawRect(tPlaceRect, tPaint);
                //
                // tPaint.setColor(color);
                // tPaint.setStrokeWidth(strokeWidth);
                // } else {
                canvas.drawRect(placeRect, paint);
                // }
            }
        }

        // if (pecaMovendo != null) {
        // paint.setColor(Color.YELLOW);
        // placeRect.set((int) (pecaMovendo.getY()),
        // (int) (pecaMovendo.getX()), pecaMovendo.getMeasuredWidth(),
        // pecaMovendo.getMeasuredHeight());
        // }

        canvas.drawRect(placeRect, paint);

        placeRect.set(0, 0, width + 1, width + 1);
        paint.setStyle(Style.STROKE);
        canvas.drawRect(placeRect, paint);
    }

    /*
     * (non-Javadoc)
     *
     * @see android.view.View#onMeasure(int, int)
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!isInEditMode()) {
            int side = min(getDisplayMetrics().widthPixels,
                getDisplayMetrics().heightPixels);

            setMeasuredDimension(side, side);
        } else {
            setMeasuredDimension(480, 480);
        }
    }

    public void onTomada(ITomadaInfo evento) throws JogadaException {
        //
    }

    /*
     * (non-Javadoc)
     *
     * @see android.view.View#onTouchEvent(android.view.MotionEvent)
     */
    public boolean onTouchEvent(MotionEvent event, Peca peca) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN :
            // this.peca = peca;

            break;
            case MotionEvent.ACTION_UP :
            // this.peca = null;

            break;
            case MotionEvent.ACTION_MOVE :
            // performDrag(event.getX(), event.getY(), this.peca);

            break;
            default :
                return true;
        }
        return true;
    }

    /**
     * @param eventX
     * @param eventY
     * @param peca
     * @return
     */
    private boolean performDrag(int i, int j, Peca peca) {

        // p.getLayoutParams() utilizado para não perder a
        // referência
        // de alinhamento com a view do tabuleiro
        LayoutParams lp = (LayoutParams) peca.getLayoutParams();

        if (lp == null)
            return false;

        int leftMargin = j * peca.getWidth();
        int topMargin = i * peca.getHeight();

        lp.leftMargin = leftMargin;
        lp.topMargin = topMargin;

        peca.setLayoutParams(lp);
        peca.invalidate();

        return true;
    }

    @TargetApi(11)
    private boolean performOnDragEvent(DragEvent event) throws Exception {
        Peca vPeca = (Peca) event.getLocalState();

        // Caso o evento seja realizado muito rápido, peca vem nulo.
        if (vPeca == null)
            return false;

        switch (event.getAction()) {
            case DragEvent.ACTION_DROP :
                int destI = (int) (event.getY() / getSquareSide());
                int destJ = (int) (event.getX() / getSquareSide());

                Log.d(TAG, String.format("I: %d, J: %d", destI, destJ));

                try {
                    IPeca peca = (IPeca) vPeca.getTag();

                    controle.mover(peca.getI(), peca.getJ(), destI, destJ);
                } catch (JogadaException e) {
                    throw e;
                }

                performDrag(destI, destJ, vPeca);

                return true;
            case DragEvent.ACTION_DRAG_STARTED :
                vPeca.hide();

                return true;
            case DragEvent.ACTION_DRAG_ENDED :
                vPeca.show();
                invalidate();

                return true;
            default :
                return true;
        }
    }
}