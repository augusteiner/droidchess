/**
 *
 */
package android.chess.dominio.pecas;

import android.chess.dominio.excecao.MovimentoInvalido;

/**
 * @author augusteiner
 *
 */
public class Bispo extends Peca {

    /**
     * @param tabuleiro
     * @param cor
     */
    public Bispo(Cor cor) {
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
        if (!super.movimentoDiagonal(destX, destY))
            throw new MovimentoInvalido(this);

        super.mover(destX, destY);
    }

}
