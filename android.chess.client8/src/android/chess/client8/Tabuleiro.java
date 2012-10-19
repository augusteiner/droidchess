/**
 *
 */
package android.chess.client8;

import android.chess.dominio.excecao.ChessException;
import android.chess.dominio.pecas.interfaces.IPeca;
import android.chess.visao.PecaAbstrata;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;

/**
 * @author augusteiner
 *
 */
public class Tabuleiro extends android.chess.visao.Tabuleiro
    implements
        OnFocusChangeListener {

    private Peca prevView;

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
     * @see
     * android.chess.visao.Tabuleiro#iniPeca(android.chess.visao.PecaAbstrata)
     */
    @Override
    protected void iniPeca(PecaAbstrata peca) {
        peca.setOnFocusChangeListener(this);
    }

    /**
     * @param peca
     * @return
     */
    private boolean jogada(Peca vPeca, int destI, int destJ) {
        IPeca peca = vPeca.getPeca();

        try {
            controle.mover(peca.getI(), peca.getJ(), destI, destJ);

            vPeca.setSelected(false);

            performDrag(destI, destJ, vPeca);

            vPeca.clearFocus();

            return true;
        } catch (ChessException e) {
            mensageiro.alertar(e.getMessage());

            return false;
        }
    }

    /**
     * @param prevView2
     * @param nextView
     */
    private void jogada(Peca prevView, Peca nextView) {
        IPeca nextPeca = nextView.getPeca();

        jogada(prevView, nextPeca.getI(), nextPeca.getJ());
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * android.view.View.OnFocusChangeListener#onFocusChange(android.view.View,
     * boolean)
     */
    /*
     * (non-Javadoc)
     *
     * @see android.chess.visao.Tabuleiro#novaPeca(android.content.Context)
     */
    @Override
    protected PecaAbstrata novaPeca(Context context) {
        return new Peca(context);
    }

    @Override
    public void onFocusChange(View nextView, boolean hasFocus) {
        if (hasFocus && nextView instanceof Peca) {
            if (prevView == null
                || controle.getTurno() == ((Peca) nextView).getPeca().getCor()) {
                prevView = (Peca) nextView;
            } else {
                jogada(prevView, (Peca) nextView);

                prevView = null;
            }
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see android.view.View#onTouchEvent(android.view.MotionEvent)
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                View v = contentView.findFocus();

                if (v == null || !(v instanceof Peca))
                    return false;

                Peca vPeca = (Peca) v;

                prevView = null;

                /*
                 * for (int i = 0; i < contentView.getChildCount(); i++) { if
                 * (contentView.getChildAt(i).isSelected() &&
                 * contentView.getChildAt(i) instanceof Peca) { vPeca = (Peca)
                 * contentView.getChildAt(i); } }
                 */

                int destI = (int) event.getY() / vPeca.getSide();
                int destJ = (int) event.getX() / vPeca.getSide();

                jogada(vPeca, destI, destJ);

            case MotionEvent.ACTION_CANCEL :
                return true;
            case MotionEvent.ACTION_UP :
                return true;
            default :
                return false;
        }
    }
}
