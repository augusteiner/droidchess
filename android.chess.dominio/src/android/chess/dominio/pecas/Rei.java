/**
 *
 */
package android.chess.dominio.pecas;

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
        // TODO Auto-generated method stub

    }

}
