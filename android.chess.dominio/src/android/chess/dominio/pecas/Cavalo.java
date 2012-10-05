/**
 *
 */
package android.chess.dominio.pecas;

import static java.lang.Math.abs;
import android.chess.dominio.excecao.MovimentoInvalido;

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

    /*
     * (non-Javadoc)
     * 
     * @see android.chess.dominio.interfaces.IPeca#mover(int, int)
     */
    @Override
    public void validar(int destI, int destJ) throws MovimentoInvalido {
        int di = abs(getI() - destI);
        int dj = abs(getJ() - destJ);

        if ((di != 1 && di != 2) || (dj != 1 && dj != 2) || di == dj)
            throw new MovimentoInvalido(this);
    }
}
