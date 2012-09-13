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
    public void mover(int destX, int destY) throws MovimentoInvalido {
        int dx = abs(getX() - destX);
        int dy = abs(getY() - destY);

        if (dx > 1 || dy > 1)
            throw new MovimentoInvalido(this);

        super.mover(destX, destY);
    }

}
