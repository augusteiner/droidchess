/**
 *
 */
package android.chess.dominio.pecas;

import android.chess.dominio.excecao.MovimentoInvalido;

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
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     *
     * @see android.chess.dominio.interfaces.IPeca#mover(int, int)
     */
    @Override
    public void mover(int destX, int destY) throws MovimentoInvalido {

        if (!movimentoHorizVert(destX, destY))
            throw new MovimentoInvalido(this);

        super.mover(destX, destY);
    }

}
