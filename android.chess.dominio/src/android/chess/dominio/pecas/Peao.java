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

    private int prevX;

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
    public void mover(int destX, int destY) throws MovimentoInvalido {
        int dy = abs(getY() - destY);
        int dx = abs(getX() - destX);
        boolean ok = true;

        if (dy != 0) {
            ok = false;
        } else if (dx > 2) {
            ok = false;
        }

        if (getMoveu()) {
            if (dx > 1)
                ok = false;
            else {
                int pdx = getX() - prevX;

                if (Math.signum(pdx) > 0) {
                    ok = destX > getX();
                } else {
                    ok = destX < getX();
                }
            }
            // ok = abs(getX() - prevX
        } else {
            //
        }

        if (!ok)
            throw new MovimentoInvalido(this);

        prevX = getX();

        super.mover(destX, destY);
    }

}
