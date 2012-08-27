/**
 *
 */
package android.chess.dominio.interfaces;

import android.chess.dominio.excecao.MovimentoInvalido;

/**
 * Interface básica para as peças que compõem o tabuleiro do jogo de xadrez.
 *
 * @author augusteiner
 *
 * @since 0.1
 */
public interface IPeca {
    /**
     * @author augusteiner
     *
     */
    public enum Cor {
        Branca, Preta;
    }

    /**
     * Tipo da peça em questão.
     *
     * @author augusteiner
     */
    public enum Tipo {
        Bispo, Cavalo, Peao, Rei, Rainha, Torre;
    }

    /**
     * @return Coordenada x atual desta peça.
     */
    int getX();

    /**
     * @return Coordenada y atual desta peça.
     */
    int getY();

    /**
     * @return Retorna o tipo desta peça.
     */
    Tipo getTipo();

    /**
     * @return Cor desta peça.
     */
    Cor getCor();

    /**
     * @param destX
     * @param destY
     * @throws MovimentoInvalido
     */
    void mover(int destX, int destY) throws MovimentoInvalido;
    /**
     * @return
     */
    @Override
    String toString();
}
