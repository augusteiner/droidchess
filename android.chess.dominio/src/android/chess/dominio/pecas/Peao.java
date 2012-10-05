/**
 *
 */
package android.chess.dominio.pecas;

import static java.lang.Math.abs;
import android.chess.dominio.excecao.MovimentoInvalido;
import android.chess.dominio.pecas.handlers.EventoMover;

/**
 * @author augusteiner
 * 
 */
public class Peao extends Peca {

    private int prevI;

    /**
     * @param tabuleiro
     * @param cor
     */
    public Peao(Cor cor) {
        super(cor);

        prevI = 7 * abs(cor.compareTo(Cor.Branca));
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.chess.dominio.pecas.Peca#onBeforeSet(int, int)
     */
    @Override
    protected void onAntesMovimento(EventoMover evento) {
        prevI = getI();
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.chess.dominio.interfaces.IPeca#mover(int, int)
     */
    @Override
    public void validar(int destI, int destJ) throws MovimentoInvalido {
        int di = abs(getI() - destI);
        int dj = abs(getJ() - destJ);
        boolean ok = true;

        if (dj != 0) {
            ok = false;
        } else if (di > 2) {
            ok = false;
        } else if (getMoveu()) {
            if (di > 1) {
                ok = false;
            } else {
                int pdi = getI() - prevI;

                if (Math.signum(pdi) > 0) {
                    ok = destI > getI();
                } else {
                    ok = destI < getI();
                }
            }
        } else {
            // Caso especial da 1ª jogada do peão.
            ok = destI != prevI;
        }

        if (!ok)
            throw new MovimentoInvalido(this);
    }
}