package android.chess.visao.views;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static java.lang.Math.min;

import java.util.Iterator;

import android.chess.controle.PartidaControle;
import android.chess.dominio.events.args.interfaces.IPromocaoArgs;
import android.chess.dominio.events.args.interfaces.ITomadaArgs;
import android.chess.dominio.events.handlers.IAntesPromocaoHandler;
import android.chess.dominio.excecao.ChessException;
import android.chess.dominio.excecao.MovimentoException;
import android.chess.dominio.pecas.interfaces.IPeca;
import android.chess.visao.Mensageiro;
import android.chess.visao.exceptions.InicializacaoException;
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
public abstract class TabuleiroAbstrato extends View implements ITabuleiro {

    // private boolean moving;
    /**
     * 
     */
    private Paint paint;
    // TODO Reabilitar no caso da implementação de suporte a uma API antiga.
    // private Peca peca;
    /**
     * 
     */
    private Rect placeRect;
    /**
     * 
     */
    protected Mensageiro mensageiro;
    // private static final String TAG = Tabuleiro.class.getSimpleName();
    /**
     * 
     */
    protected PartidaControle partidaCtrl;
    /**
     * 
     */
    private IAntesPromocaoHandler onAntesPromocaoUiHandler = null;
    /**
     * 
     */
    private static final long serialVersionUID = 8324111699374876004L;

    /**
     * @param context
     */
    public TabuleiroAbstrato(Context context) {
        super(context);

        initPrivate();
    }

    /**
     * @param context
     * @param attrs
     */
    public TabuleiroAbstrato(Context context, AttributeSet attrs) {
        super(context, attrs);

        initPrivate();
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public TabuleiroAbstrato(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        initPrivate();
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
     * Prepara objetos para desenho deste tabuleiro.
     * 
     * @throws Exception
     */
    public void init(IAntesPromocaoHandler promotionHandler,
            ViewGroup contentView) throws InicializacaoException {

        this.onAntesPromocaoUiHandler = promotionHandler;

        // try {
        // partidaCtrl.convidar(null, new IAsyncCallback<IPartida>() {
        //
        // public void invoke(IPartida arg) {
        // }
        // });
        // } catch (ExecucaoException e) {
        // mensageiro.erro(e);
        // }

        contentView.removeAllViews();
        contentView.addView(this);

        initPecas(contentView);
    }

    /**
     * Método deve ser utilizado para iniciar algo mais nas classes que herdem
     * desta.
     * 
     * @param peca
     */
    protected void initPeca(PecaAbstrata peca) {
        // do nothing.
    }

    /**
     * @param contentView
     * 
     */
    protected void initPecas(ViewGroup contentView) {
        Iterator<IPeca> pecas = this.partidaCtrl.getTabuleiro().getPecas();
        IPeca next = null;
        Context context = getContext();

        PecaAbstrata peca = null;

        LayoutParams lp = new LayoutParams(WRAP_CONTENT, WRAP_CONTENT);

        while (pecas.hasNext()) {
            peca = this.novaPeca(context);
            this.initPeca(peca);

            next = pecas.next();
            peca.setTag(next);
            peca.init();

            peca.addOnPromocaoHandler(this.onAntesPromocaoUiHandler);

            lp = new LayoutParams(lp);

            // LayoutParams não copia as "rules" definidas.
            lp.addRule(RelativeLayout.ALIGN_TOP, getId());

            lp.topMargin = peca.getLado() * next.getI();
            lp.leftMargin = peca.getLado() * next.getJ();

            contentView.addView(peca, lp);
        }
    }

    /**
     *
     */
    private void initPrivate() {
        this.mensageiro = new Mensageiro(getContext());

        try {
            this.partidaCtrl = new PartidaControle();
        } catch (Exception e) {
            e.printStackTrace();

            this.mensageiro.erro(e);

            return;
        }

        this.placeRect = new Rect();
        this.paint = new Paint();

        setBackgroundColor(Color.WHITE);
    }

    /**
     * @param context
     * @return
     */
    protected abstract PecaAbstrata novaPeca(Context context);

    /**
     * @param sender
     * @param info
     * @throws ChessException
     */
    public void onAntesPromocao(IPromocaoArgs info) throws ChessException {

        this.onAntesPromocaoUiHandler.onAntesPromocao(info);
    }

    public void onDepoisPromocao(Object sender, IPromocaoArgs evento)
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

        this.paint.setColor(Color.BLACK);
        this.paint.setStyle(Style.FILL);

        for (int i = 0, j; i < 8; i++) {
            for (j = (i + 1) % 2; j < 8; j += 2) {
                this.placeRect.set(i * tPlaceSide,// left,
                        j * tPlaceSide,// top,
                        (i + 1) * tPlaceSide,// right,
                        (j + 1) * tPlaceSide// bottom
                        );
                canvas.drawRect(this.placeRect, this.paint);
            }
        }

        // if (pecaMovendo != null) {
        // paint.setColor(Color.YELLOW);
        // placeRect.set((int) (pecaMovendo.getY()),
        // (int) (pecaMovendo.getX()), pecaMovendo.getMeasuredWidth(),
        // pecaMovendo.getMeasuredHeight());
        // }

        canvas.drawRect(this.placeRect, this.paint);

        this.placeRect.set(0, 0, width + 1, width + 1);
        this.paint.setStyle(Style.STROKE);
        canvas.drawRect(this.placeRect, this.paint);
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

    /**
     * @param evento
     * @throws MovimentoException
     */
    public void onTomada(ITomadaArgs evento) throws MovimentoException {
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
        // referência do alinhamento com esta view.
        LayoutParams lp = (LayoutParams) peca.getLayoutParams();

        if (lp == null) {
            return false;
        }

        int leftMargin = destJ * peca.getWidth();
        int topMargin = destI * peca.getHeight();

        lp.leftMargin = leftMargin;
        lp.topMargin = topMargin;

        peca.setLayoutParams(lp);
        peca.invalidate();

        return true;
    }
}