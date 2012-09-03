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
public class Cavalo extends Peca {

    /**
     * @param tabuleiro
     * @param cor
     */
    public Cavalo(Cor cor) {
        super(cor);
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     *
     * @see android.chess.dominio.interfaces.IPeca#mover(int, int)
     */
    @Override
    public void mover(int destX, int destY) throws MovimentoInvalido {
        int dx = abs(getX() - destX);
        int dy = abs(getY() - destY);

        if ((dx != 1 && dx != 2) || (dy != 1 && dy != 2) || dx == dy)
            throw new MovimentoInvalido(this);

        super.mover(destX, destY);
    }
}
