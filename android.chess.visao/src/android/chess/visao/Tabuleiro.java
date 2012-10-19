package android.chess.visao;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static java.lang.Math.min;

import java.util.Iterator;

import android.chess.controle.PartidaControle;
import android.chess.dominio.events.info.interfaces.IPromocaoInfo;
import android.chess.dominio.events.info.interfaces.ITomadaInfo;
import android.chess.dominio.excecao.ChessException;
import android.chess.dominio.excecao.MovimentoException;
import android.chess.dominio.pecas.interfaces.IPeca;
import android.chess.visao.interfaces.ITabuleiro;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

/**
 * @author augusteiner
 *
 */
public abstract class Tabuleiro extends View implements ITabuleiro {

    // private static final String TAG = Tabuleiro.class.getSimpleName();
    protected PartidaControle controle;
    protected Mensageiro mensageiro;
    // private boolean moving;
    private Paint paint;
    // TODO Reabilitar no caso da implementação de suporte a uma API antiga.
    // private Peca peca;
    private Rect placeRect;
    protected ViewGroup contentView;

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
        return getContext().getResources().getDisplayMetrics();
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
     * Método deve ser utilizado para iniciar algo mais nas classes que herdem
     * desta.
     *
     * @param peca
     */
    protected void iniPeca(PecaAbstrata peca) {
        //do nothing.
    }

    /**
     *
     */
    private void init() {
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
        this.contentView = contentView;

        controle.novaPartida();

        contentView.removeAllViews();
        contentView.addView(this);

        initPecas();
    }

    /**
     * @param contentView
     *
     */
    protected void initPecas() {
        Iterator<IPeca> pecas = controle.getTabuleiro().getPecas();
        IPeca next = null;
        Context context = getContext();

        PecaAbstrata peca = null;

        LayoutParams lp = new LayoutParams(WRAP_CONTENT, WRAP_CONTENT);

        while (pecas.hasNext()) {
            peca = this.novaPeca(context);
            this.iniPeca(peca);

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

    /**
     * @param context
     * @return
     */
    protected abstract PecaAbstrata novaPeca(Context context);

    /**
     * @param sender
     * @param evento
     * @throws ChessException
     */
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

    public void onTomada(ITomadaInfo evento) throws MovimentoException {
        //
    }

    /**
     * @param destI
     * @param destJ
     * @param peca
     * @return
     */
    protected boolean performDrag(int destI, int destJ, PecaAbstrata peca) {

        // p.getLayoutParams() utilizado para não perder a
        // referência
        // de alinhamento com a view do tabuleiro
        LayoutParams lp = (LayoutParams) peca.getLayoutParams();

        if (lp == null)
            return false;

        int leftMargin = destJ * peca.getWidth();
        int topMargin = destI * peca.getHeight();

        lp.leftMargin = leftMargin;
        lp.topMargin = topMargin;

        peca.setLayoutParams(lp);
        peca.invalidate();

        return true;
    }
}