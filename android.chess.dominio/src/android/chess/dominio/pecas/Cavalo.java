/**
 *
 */
package android.chess.dominio.pecas;

import static java.lang.Math.abs;
import android.chess.dominio.Peca;
import android.chess.dominio.excecao.ChessException;
import android.chess.dominio.excecao.MovimentoInvalidoException;

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

    /**
     * @param peao
     */
    Cavalo(Peao peao) {
        super(peao);
    }

    /*
     * (non-Javadoc)
     *
     * @see android.chess.dominio.interfaces.IPeca#mover(int, int)
     */
    @Override
    public void validarJogada(int destI, int destJ) throws ChessException {
        int di = abs(getI() - destI);
        int dj = abs(getJ() - destJ);

        if ((di != 1 && di != 2) || (dj != 1 && dj != 2) || di == dj)
            throw new MovimentoInvalidoException(this);
    }
}
