/**
 *
 */
package android.chess.dominio.pecas;

import android.chess.dominio.excecao.MovimentoInvalidoException;

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
    Bispo(Peao peca) {
        super(peca);
    }

    /*
     * (non-Javadoc)
     *
     * @see android.chess.dominio.interfaces.IPeca#mover(int, int)
     */
    @Override
    public void validarJogada(int destI, int destJ) throws MovimentoInvalidoException {
        if (!super.movimentoDiagonal(destI, destJ))
            throw new MovimentoInvalidoException(this);
    }

}
