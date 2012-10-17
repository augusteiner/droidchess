/**
 *
 */
package android.chess.dominio.pecas;

import android.chess.dominio.excecao.MovimentoInvalidoException;

/**
 * @author augusteiner
 *
 */
public class Torre extends Peca {

    /**
     * @param tabuleiro
     * @param cor
     */
    public Torre(Cor cor) {
        super(cor);
    }

    /**
     * @param peao
     */
    Torre(Peao peao) {
        super(peao);
    }

    /*
     * (non-Javadoc)
     *
     * @see android.chess.dominio.interfaces.IPeca#mover(int, int)
     */
    @Override
    public void validarJogada(int destI, int destJ) throws MovimentoInvalidoException {

        if (!movimentoHorizVert(destI, destJ))
            throw new MovimentoInvalidoException(this);
    }
}