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
public class Rei extends Peca {

    /**
     *
     */
    public Rei(Cor cor) {
        super(cor);
    }

    /* (non-Javadoc)
     * @see android.chess.dominio.interfaces.IPeca#mover(int, int)
     */
    @Override
    public void mover(int destI, int destJ) throws MovimentoInvalido {
        int di = abs(getI() - destI);
        int dj = abs(getJ() - destJ);

        if (di > 1 || dj > 1)
            throw new MovimentoInvalido(this);

        super.mover(destI, destJ);
    }

}
