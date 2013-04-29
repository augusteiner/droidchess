package android.chess.app.views;

import android.annotation.TargetApi;
import android.chess.dominio.excecao.ChessException;
import android.chess.dominio.excecao.MovimentoException;
import android.chess.dominio.pecas.interfaces.IPeca;
import android.chess.visao.views.TabuleiroAbstrato;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;

/**
 * @author augusteiner
 *
 */
public class Tabuleiro extends TabuleiroAbstrato {

    /**
     *
     */
    private static final long serialVersionUID = 6555426123534972054L;
    /**
     *
     */
    private static final String TAG = Tabuleiro.class.getName();

    /**
     * @param context
     */
    public Tabuleiro(Context context) {
        super(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public Tabuleiro(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public Tabuleiro(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
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
        } catch (ChessException e) {
            mensageiro.alertar(e.getMessage());

            return false;
        } catch (Exception e) {
            mensageiro.erro(e);

            Log.d(TAG, e.toString());

            return false;
        }
    }

    /**
     * @param event
     * @return
     * @throws Exception
     */
    @TargetApi(11)
    private boolean performOnDragEvent(DragEvent event) throws Exception {
        Peca vPeca = (Peca) event.getLocalState();

        /*
         * Caso o evento seja realizado muito rápido, peca vem nulo.
         */
        if (vPeca == null)
            return false;

        switch (event.getAction()) {
            case DragEvent.ACTION_DROP :
                int destI = (int) (event.getY() / getSquareSide());
                int destJ = (int) (event.getX() / getSquareSide());

                Log.d(TAG, String.format("I: %d, J: %d", destI, destJ));

                try {
                    IPeca peca = (IPeca) vPeca.getTag();

                    partidaCtrl.mover(peca.getI(), peca.getJ(), destI, destJ);
                } catch (MovimentoException e) {
                    throw e;
                }

                performDrag(destI, destJ, vPeca);

                return true;
            case DragEvent.ACTION_DRAG_STARTED :
                // vPeca.esconder();

                return true;
            case DragEvent.ACTION_DRAG_ENDED :
                // vPeca.exibir();
                // invalidate();

                return true;
            default :
                return true;
        }
    }

    @Override
    protected Peca novaPeca(Context context) {
        return new Peca(context);
    }
}
