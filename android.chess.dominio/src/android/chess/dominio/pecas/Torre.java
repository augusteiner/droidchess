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
public class Torre extends Peca {

    /**
     *
     */
    private static final long serialVersionUID = -2050588410243308221L;

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
    public void validarJogada(int destI, int destJ) throws ChessException {

        if (!movimentoHorizVert(destI, destJ))
            throw new MovimentoException(this, destI, destJ);
    }
}