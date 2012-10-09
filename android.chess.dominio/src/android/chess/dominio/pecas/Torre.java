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
    public void validarJogada(int destI, int destJ) throws MovimentoInvalido {

        if (!movimentoHorizVert(destI, destJ))
            throw new MovimentoInvalido(this);
    }
}