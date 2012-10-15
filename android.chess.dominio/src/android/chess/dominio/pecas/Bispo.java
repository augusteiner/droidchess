/**
 *
 */
package android.chess.dominio.pecas;

import android.chess.dominio.excecao.MovimentoInvalido;
import android.chess.dominio.interfaces.IPeca;

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
    }

    /**
     * @param peca
     */
    Bispo(IPeca peca) {
        super(peca);
    }

    /*
     * (non-Javadoc)
     *
     * @see android.chess.dominio.interfaces.IPeca#mover(int, int)
     */
    @Override
    public void validarJogada(int destI, int destJ) throws MovimentoInvalido {
        if (!super.movimentoDiagonal(destI, destJ))
            throw new MovimentoInvalido(this);
    }

}
