/**
 *
 */
package android.chess.dominio.pecas;

import android.chess.dominio.Peca;
import android.chess.dominio.excecao.ChessException;
import android.chess.dominio.excecao.MovimentoException;

/**
 * @author augusteiner
 *
 */
public class Bispo extends Peca {

    /**
     *
     */
    private static final long serialVersionUID = 7006893619030472559L;

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
    public void validarJogada(int destI, int destJ) throws ChessException {
        if (!super.movimentoDiagonal(destI, destJ))
            throw new MovimentoException(this, destI, destJ);
    }

}
