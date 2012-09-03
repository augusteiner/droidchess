/**
 *
 */
package android.chess.dominio.interfaces;

import android.chess.dominio.Jogada;
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
     * @return Cor desta peça.
     */
    Cor getCor();

    /**
     * @return Retorna o tipo desta peça.
     */
    Tipo getTipo();

    /**
     * @return Coordenada x atual desta peça.
     */
    int getX();

    /**
     * @return Coordenada y atual desta peça.
     */
    int getY();

    /**
     * @param destX
     * @param destY
     * @throws MovimentoInvalido
     */
    void mover(int destX, int destY) throws MovimentoInvalido;

    /**
     * Retorna se o movimento da peça na jogada é na diagonal. Útil para
     * validação de movimentos de bispos.
     *
     * @param jogada
     * @return
     */
    boolean movimentoDiagonal(Jogada jogada);

    /**
     * @param destY
     * @return
     */
    boolean movimentoHorizontal(int destY);

    /**
     * Retorna se o movimento da peça na jogada é horizontal. Útil para
     * validação de movimentos de torres.
     *
     * @param jogada
     * @return
     */
    boolean movimentoHorizontal(Jogada jogada);

    /**
     * @param destX
     * @param destY
     * @return
     */
    boolean movimentoHorizDiag(int destX, int destY);
    /**
     * Retorna se o movimento da peça na jogada é vertical ou horizontal. Útil
     * para validação de movimentos de bispos, torres e rainhas.
     *
     * @param jogada
     * @return
     */
    boolean movimentoHorizVert(Jogada jogada);

    /**
     * @param x
     * @param y
     */
    void set(int x, int y);

    /**
     * @param x
     */
    void setX(int x);

    /**
     * @param y
     */
    void setY(int y);

    /**
     * @return
     */
    @Override
    String toString();
}
