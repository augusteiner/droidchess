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
        Bispo, Cavalo, Peao, Rainha, Rei, Torre;
    }

    /**
     * @return Cor desta peça.
     */
    Cor getCor();

    /**
     * @return Linha atual desta peça.
     */
    int getI();

    /**
     * @return Coluna atual desta peça.
     */
    int getJ();

    /**
     * @return Retorna o tipo desta peça.
     */
    Tipo getTipo();

    /**
     * @param destI
     * @param destJ
     * @throws MovimentoInvalido
     */
    void mover(int destI, int destJ) throws MovimentoInvalido;

    /**
     * @param jogada
     *
     * @throws MovimentoInvalido
     *
     * @see {@link IPeca#mover(int, int)}
     */
    void mover(Jogada jogada) throws MovimentoInvalido;

    /**
     * Retorna se o movimento da peça na jogada é na diagonal. Útil para
     * validação de movimentos de bispos.
     *
     * @param destI
     * @param destJ
     *
     * @return
     */
    boolean movimentoDiagonal(int destI, int destJ);

    /**
     * Retorna se o movimento da peça na jogada é vertical ou horizontal. Útil
     * para validação de movimentos de bispos, torres e rainhas.
     *
     * @param destI
     * @param destJ
     * @return
     */
    boolean movimentoHorizDiag(int destI, int destJ);

    /**
     * Retorna se o movimento da jogada é horizontal.
     *
     * @param jogada
     *            Jogada a ser testado o tipo de movimento.
     *
     * @return {@link Boolean} True caso o movimento seja na horizontal, False
     *         caso contrário.
     */
    boolean movimentoHorizontal(int destJ);

    /**
     * @param destI
     * @param destJ
     * @return
     */
    boolean movimentoHorizVert(int destI, int destJ);

    /**
     *
     * @param destI
     *
     * @param destJ
     *
     * @return
     */
    boolean movimentoVertical(int destI, int destJ);

    /**
     * @param i
     * @param j
     */
    void set(int i, int j);

    /**
     * @param i
     */
    void setI(int i);

    /**
     * @param j
     */
    void setJ(int j);

    /**
     * @return
     */
    @Override
    String toString();
}
