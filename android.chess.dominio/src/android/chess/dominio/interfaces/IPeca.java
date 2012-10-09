/**
 *
 */
package android.chess.dominio.interfaces;

import android.chess.dominio.excecao.ChessException;
import android.chess.dominio.excecao.JogadaException;
import android.chess.dominio.excecao.MovimentoInvalido;
import android.chess.dominio.interfaces.handlers.ITomadaHandler;
import android.chess.dominio.pecas.Peca;

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

        /**
         * @return
         */
        public Cor outra() {
            if (compareTo(Branca) == 0)
                return Preta;
            else
                return Branca;
        }
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
     * @param jogada
     *
     * @throws JogadaException
     *
     * @see IPeca#mover(int, int)
     */
    void mover(IJogada jogada, Peca outra) throws ChessException;

    /**
     * @param destI
     * @param destJ
     * @throws ChessException
     */
    void mover(int destI, int destJ) throws ChessException;

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
     */
    void setI(int i);

    /**
     * @param j
     */
    void setJ(int j);

    /**
     * @param onTomadaHandler
     *
     * @todo Melhor setOnVisualFeedbackHandler?
     */
    void setOnTomadaHandler(ITomadaHandler onTomadaHandler);

    /**
     * @return Representação desta peça como {@link String}
     */
    @Override
    String toString();

    /**
     * Valida o movimento da peça em questão de acordo com a jogada
     * especificada.
     *
     * @param jogada
     *            Jogada a ser realizada com a peça em questão.
     *
     * @throws ChessException
     *             Caso o movimento seja inválido esta exceção será jogada.
     */
    void validarJogada(IJogada jogada) throws ChessException;

    /**
     * Valida o movimento da peça em questão de acordo com as posições de
     * destino informadas.
     *
     * @param destI
     *            Linha de destino.
     *
     * @param destJ
     *            Coluna de destino.
     *
     * @throws MovimentoInvalido
     *             Caso o movimento seja inválido esta exceção será jogada.
     */
    void validarJogada(int destI, int destJ) throws MovimentoInvalido;
}