/**
 *
 */
package android.chess.dominio.pecas;

import static java.lang.Math.abs;
import android.chess.dominio.excecao.MovimentoInvalido;

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
    }

    /*
     * (non-Javadoc)
     *
     * @see android.chess.dominio.interfaces.IPeca#mover(int, int)
     */
    @Override
    public void mover(int destI, int destJ) throws MovimentoInvalido {
        int di = abs(getI() - destI);
        int dj = abs(getJ() - destJ);
        boolean ok = true;

        if (dj != 0) {
            ok = false;
        } else if (di > 2) {
            ok = false;
        }

        if (getMoveu()) {
            if (di > 1)
                ok = false;
            else {
                int pdi = getI() - prevI;

                if (Math.signum(pdi) > 0) {
                    ok = destI > getI();
                } else {
                    ok = destI < getI();
                }
            }
            // ok = abs(getX() - prevX
        } else {
            //
        }

        if (!ok)
            throw new MovimentoInvalido(this);

        prevI = getI();

        super.mover(destI, destJ);
    }

}
