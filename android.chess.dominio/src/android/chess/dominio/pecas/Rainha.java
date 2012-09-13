/**
 *
 */
package android.chess.dominio.pecas;

import android.chess.dominio.excecao.MovimentoInvalido;

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
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     *
     * @see android.chess.dominio.interfaces.IPeca#mover(int, int)
     */
    @Override
    public void mover(int destX, int destY) throws MovimentoInvalido {
        if (!movimentoHorizDiag(destX, destY)
                && !movimentoVertical(destX, destY))
            throw new MovimentoInvalido(this);

        super.mover(destX, destY);
    }

}
