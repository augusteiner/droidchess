package android.chess.visao.views;

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
import android.chess.visao.R;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

/**
 * Classe abstrata para representação gráfica de uma peça do tabuleiro.
 * 
 * @author augusteiner
 */
public abstract class PecaAbstrata extends View
    implements
        IAntesPromocaoHandler,
        IDepoisPromocaoHandler,
        ITomadaHandler,
        IMovimentoHandler {

    /**
     * Tag para utilização de métodos de log.
     */
    private static final String TAG = PecaAbstrata.class.getSimpleName();

    /**
     * @param context
     */
    public PecaAbstrata(Context context) {
        super(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public PecaAbstrata(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public PecaAbstrata(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * Adiciona um handler do evento de promoção a fila de execução.
     * 
     * @param onPromocaoHandler
     *            Handler a ser adicionado.
     */
    public void addOnPromocaoHandler(IAntesPromocaoHandler onPromocaoHandler) {
        ((Peao) getPeca()).addOnAntesPromocaoHandler(onPromocaoHandler);
    }

    /**
     * Adiciona um handler do evento de tomada a fila de execução.
     * 
     * @param onTomadaHandler
     *            Handler a ser adicionado.
     */
    public void addOnTomadaHandler(ITomadaHandler onTomadaHandler) {
        getPeca().addOnTomadaHandler(onTomadaHandler);
    }
    /**
     * Retorna o resourse_id de acordo com a cor e nome da peça configurada com
     * o setTag.
     * 
     * @return Id do recurso associado com a imagem da peça de chadrez.
     * 
     * @see View#setTag()
     */
    private int backgroundResId() {
        IPeca peca = (IPeca) getTag();

        if (peca == null)
            return 0;

        String bgName = String.format("%s_%s", peca.getCor().toString()
            .substring(0, 1).toLowerCase(), peca.getClass().getSimpleName()
            .toLowerCase());

        try {
            /*
             * Log.d(TAG, String.format("Resource class is %s.",
             * R.class.getName()));
             */
            return R.drawable.class.getDeclaredField(bgName).getInt(null);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Esconde esta peça. Cancelando chamada anterior ao método
     * {@link #exibir()}.
     * 
     * @deprecated
     */
    @Deprecated
    public void esconder() {
        setVisibility(INVISIBLE);
        invalidate();
    }

    /**
     * Exibe esta peça. Cancelando chamada anterior ao método
     * {@link #esconder()} .
     * 
     * @deprecated
     */
    @Deprecated
    public void exibir() {
        setVisibility(VISIBLE);
        invalidate();
    }

    /**
     * Retorna as métricas do display associado aos recursos desta view.
     * 
     * @return Objeto para as métricas do display.
     */
    private DisplayMetrics getDisplayMetrics() {
        return getContext().getResources().getDisplayMetrics();
    }

    /**
     * Retorna o lado do quadrado desta view de acordo com as métricas do
     * display.
     * 
     * @return Número inteiro representando o tamanho do lado desta view
     *         (cosiderada como um quadrado).
     */
    public int getLado() {
        return (min(getDisplayMetrics().widthPixels,
            getDisplayMetrics().heightPixels) / 8);
    }

    /**
     * Atalho para (IPeca)getTag(). Retorna getTag deste objeto com cast para
     * IPeca.
     * 
     * @return A peça associada a esta view do android.
     * 
     * @see View#getTag()
     */
    public IPeca getPeca() {
        return (IPeca) getTag();
    }
    /**
     * Inicializa informações desta peça, como imagem de fundo entre outros.
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
     * android.chess.dominio.events.handlers.IAntesPromocaoHandler#onAntesPromocao
     * (android.chess.dominio.events.info.interfaces.IPromocaoInfo)
     */
    public void onAntesPromocao(IPromocaoInfo info) throws ChessException {
        Log.d(TAG, String.format("Promoção da peça %s.", info.getAlvo()));

        // FIXME Remover chamada daqui, método deve ser executado na UI.
        info.callback();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.chess.dominio.events.handlers.IDepoisPromocaoHandler#onDepoisPromocao
     * (android.chess.dominio.events.info.interfaces.IPromocaoInfo)
     */
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
        setMeasuredDimension(getLado(), getLado());
    }

    public void onMovimento(Object sender, IMovimentoInfo evento)
        throws ChessException {

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.chess.dominio.events.handlers.ITomadaHandler#onTomada(android
     * .chess.dominio.events.info.interfaces.ITomadaInfo)
     */
    public void onTomada(ITomadaInfo evento) throws MovimentoException {
        setVisibility(INVISIBLE);
        invalidate();

        // TODO Utilizar handler para remover peça do tabuleiro?
        // ViewGroup g = (ViewGroup) getParent();
    }
}