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
public class Rainha extends Peca {

    /**
     * @param tabuleiro
     * @param cor
     */
    public Rainha(Cor cor) {
        super(cor);
    }

    /**
     * @param peao
     */
    Rainha(Peao peao) {
        super(peao);
    }

    /*
     * (non-Javadoc)
     *
     * @see android.chess.dominio.interfaces.IPeca#mover(int, int)
     */
    @Override
    public void validarJogada(int destI, int destJ) throws ChessException {
        if (!movimentoHorizDiag(destI, destJ)
            && !movimentoVertical(destI, destJ))
            throw new MovimentoException(this, destI, destJ);
    }
}