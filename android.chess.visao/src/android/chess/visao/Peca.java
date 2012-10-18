package android.chess.visao;

import static java.lang.Math.min;
import android.chess.dominio.events.handlers.IAntesPromocaoHandler;
import android.chess.dominio.events.handlers.IDepoisPromocaoHandler;
import android.chess.dominio.events.handlers.IMovimentoHandler;
import android.chess.dominio.events.handlers.ITomadaHandler;
import android.chess.dominio.events.info.interfaces.IMovimentoInfo;
import android.chess.dominio.events.info.interfaces.IPromocaoInfo;
import android.chess.dominio.events.info.interfaces.ITomadaInfo;
import android.chess.dominio.excecao.ChessException;
import android.chess.dominio.excecao.MovimentoException;
import android.chess.dominio.pecas.Peao;
import android.chess.dominio.pecas.interfaces.IPeao;
import android.chess.dominio.pecas.interfaces.IPeca;
import android.chess.dominio.pecas.interfaces.IPeca.Tipo;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

/**
 * Representa graficamente uma peça de um tabuleiro.
 *
 * @author augusteiner
 *
 */
public abstract class Peca extends View
    implements
        IAntesPromocaoHandler,
        IDepoisPromocaoHandler,
        ITomadaHandler,
        IMovimentoHandler {

    private static final String TAG = Peca.class.getSimpleName();

    /**
     * @param context
     */
    public Peca(Context context) {
        super(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public Peca(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public Peca(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * @param onPromocaoHandler
     */
    public void addOnPromocaoHandler(IAntesPromocaoHandler onPromocaoHandler) {
        ((Peao) getPeca()).addOnAntesPromocaoHandler(onPromocaoHandler);
    }

    /**
     * @param onTomadaHandler
     */
    public void addOnTomadaHandler(ITomadaHandler onTomadaHandler) {
        getPeca().addOnTomadaHandler(onTomadaHandler);
    }
    /**
     * Retorna o resid de acordo com a cor e nome da peça configurada com o
     * setTag.
     *
     * @return Id do recurso associado com a imagem da peça de chadrez.
     *
     * @see View#setTag(Object)
     */
    public int backgroundResId() {
        IPeca peca = (IPeca) getTag();

        if (peca == null)
            return 0;

        String bgName = String.format("%s_%s", peca.getCor().toString()
            .substring(0, 1).toLowerCase(), peca.getClass().getSimpleName()
            .toLowerCase());

        try {
            return R.drawable.class.getDeclaredField(bgName).getInt(null);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Retorna a linha desta peça em relação ao tabuleiro.
     *
     * @return
     */
    public abstract int getCoordI();

    /**
     * Retorna a coordenada y desta peça em relação ao tabuleiro.
     *
     * @return
     */
    public abstract int getCoordJ();

    /**
     * @return
     */
    private DisplayMetrics getDisplayMetrics() {
        return getContext().getResources().getDisplayMetrics();
    }

    /**
     * @return
     */
    private IPeca getPeca() {
        return (IPeca) getTag();
    }

    /**
     * @return
     */
    public int getSide() {
        return (min(getDisplayMetrics().widthPixels,
            getDisplayMetrics().heightPixels) / 8);
    }

    /**
     * @param peca
     */
    public void hide() {
        // setVisibility(INVISIBLE);
        // invalidate();
    }
    /**
     *
     */
    public void init() {
        setBackgroundResource(backgroundResId());

        // Peça da camada de modelo.
        IPeca peca = getPeca();

        // TODO Melhorar abordagem!
        if (getPeca().getTipo() == Tipo.Peao) {
            IPeao peao = (IPeao) peca;

            peao.addOnAntesPromocaoHandler(this);
            peao.addOnDepoisPromocaoHandler(this);
        }

        peca.addOnTomadaHandler(this);
        peca.addOnMovimentoHandler(this);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * android.chess.dominio.interfaces.handlers.IPromocaoHandler#onAntesPromocao
     * (android.chess.dominio.interfaces.IPromocaoInfo)
     */
    @Override
    public void onAntesPromocao(IPromocaoInfo info) throws ChessException {
        Log.d(TAG, String.format("Promoção da peça %s.", info.getAlvo()));

        // FIXME Remover chamada daqui, método deve ser executado na UI.
        info.callback();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * android.chess.dominio.interfaces.handlers.IPromocaoHandler#onDepoisPromocao
     * (android.chess.dominio.interfaces.IPromocaoInfo)
     */
    @Override
    public void onDepoisPromocao(IPromocaoInfo info) throws ChessException {
        Log.d(TAG, String.format("Peão promovido a %s.", info.getAlvo()));

        setTag(info.getAlvo());

        init();
        invalidate();
    }

    /*
     * (non-Javadoc)
     *
     * @see android.view.View#onDraw(android.graphics.Canvas)
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // pPaint.setColor(Color.LTGRAY);
        // pPaint.setStyle(Style.FILL);
        //
        // int width = getMeasuredWidth();
        //
        // canvas.drawCircle(width / 2, width / 2, width / 2 - 4, pPaint);
    }

    /*
     * (non-Javadoc)
     *
     * @see android.view.View#onMeasure(int, int)
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getSide(), getSide());
    }

    @Override
    public void onMovimento(Object sender, IMovimentoInfo evento)
        throws ChessException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * android.chess.dominio.interfaces.handlers.ITomadaHandler#onTomada(android
     * .chess.dominio.interfaces.IEventoTomada)
     */
    @Override
    public void onTomada(ITomadaInfo evento) throws MovimentoException {
        setVisibility(INVISIBLE);
        invalidate();

        // TODO Utilizar handler para remover peça do tabuleiro?
        // ViewGroup g = (ViewGroup) getParent();
    }

    /**
     * @param peca
     */
    public void show() {
        // setVisibility(VISIBLE);
        // invalidate();
    }
}