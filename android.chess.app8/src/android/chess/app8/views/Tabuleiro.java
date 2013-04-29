/**
 *
 */
package android.chess.app8.views;

import android.chess.dominio.events.handlers.IAntesPromocaoHandler;
import android.chess.dominio.pecas.interfaces.IPeca;
import android.chess.visao.exceptions.InicializacaoException;
import android.chess.visao.views.PecaAbstrata;
import android.chess.visao.views.TabuleiroAbstrato;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;

/**
 * @author augusteiner
 * 
 */
public class Tabuleiro extends TabuleiroAbstrato implements
    OnFocusChangeListener {
    /**
     *
     */
    private Peca prevView;
    /**
     * 
     */
    private ViewGroup contentView;
    /**
     *
     */
    private static final long serialVersionUID = -829632165772869504L;

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
     * android.view.View.OnFocusChangeListener#onFocusChange(android.view.View,
     * boolean)
     */
    public void onFocusChange(View nextView, boolean hasFocus) {
        if (hasFocus && nextView instanceof Peca) {
            if (prevView == null
                || partidaCtrl.getTurno() == ((Peca) nextView).getPeca()
                    .getCor()) {
                prevView = (Peca) nextView;
            } else {
                prevView.requestFocus();

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
            View v = contentView.getFocusedChild();

            if (v == null || !(v instanceof Peca))
                return false;

            Peca vPeca = (Peca) v;

            prevView = null;

            int destI = (int) event.getY() / vPeca.getLado();
            int destJ = (int) event.getX() / vPeca.getLado();

            jogada(vPeca, destI, destJ);

        case MotionEvent.ACTION_CANCEL :
            return true;
        case MotionEvent.ACTION_UP :
            return true;
        default :
            return false;
        }
    }

    @Override
    public void init(IAntesPromocaoHandler handler, ViewGroup contentView)
        throws InicializacaoException {
        super.init(handler, contentView);

        this.contentView = contentView;
    }

    /**
     * @param peca
     * @return
     */
    private boolean jogada(Peca vPeca, int destI, int destJ) {
        // FIXME Possívelmente passar o id da view!
        if (vPeca == null)
            return false;

        IPeca peca = vPeca.getPeca();

        try {
            partidaCtrl.mover(peca.getI(), peca.getJ(), destI, destJ);

            vPeca.setSelected(false);

            performDrag(destI, destJ, vPeca);

            // vPeca.clearFocus();

            return true;
        } catch (Exception e) {
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
     * android.chess.visao.Tabuleiro#iniPeca(android.chess.visao.PecaAbstrata)
     */
    @Override
    protected void initPeca(PecaAbstrata peca) {
        peca.setOnFocusChangeListener(this);
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
}
