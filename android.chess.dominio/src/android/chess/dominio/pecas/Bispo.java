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
    public void validar(int destI, int destJ) throws MovimentoInvalido {
        if (!super.movimentoDiagonal(destI, destJ))
            throw new MovimentoInvalido(this);
    }

}
