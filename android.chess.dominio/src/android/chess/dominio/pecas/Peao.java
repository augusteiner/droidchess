/**
 *
 */
package android.chess.dominio.pecas;

import android.chess.dominio.excecao.MovimentoInvalido;

/**
 * @author augusteiner
 *
 */
public class Peao extends Peca {

    /**
     * @param tabuleiro
     * @param cor
     */
    public Peao(Cor cor) {
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
        //TODO Implementar lógica de validação do movimento

        this.setX(destX);
        this.setY(destY);
    }

}
