/**
 *
 */
package android.chess.dominio.pecas.interfaces;

import java.io.Serializable;

import android.chess.dominio.Peca;
import android.chess.dominio.events.handlers.IMovimentoHandler;
import android.chess.dominio.events.handlers.ITomadaHandler;
import android.chess.dominio.excecao.ChessException;
import android.chess.dominio.interfaces.IJogada;

/**
 * Interface básica para as peças que compõem o tabuleiro do jogo de xadrez.
 *
 * @author augusteiner
 *
 * @since 0.1
 */
public interface IPeca extends Serializable {
    /**
     * Enum para diferenciar peças de um jogador.
     *
     * @author augusteiner
     */
    public enum Cor {
        /**
         * Cor de peça branca.
         */
        Branca,
        /**
         * Cor de peça preta.
         */
        Preta;
        /**
         * Dado que o valor deste enum retorna o seu valor oposto (levando em
         * consideração que só há dois valores possíveis).
         *
         * @return A cor oposta a atual deste enum.
         */
        public Cor outra() {
            if (compareTo(Branca) == 0)
                return Preta;
            else
                return Branca;
        }
    }
    /**
     * Tipos de peça consideradas em um jogo de xadrez.
     *
     * @author augusteiner
     */
    public enum Tipo {
        /**
         * Bispo somente se move nas diagonais.
         */
        Bispo,
        /**
         * Cavalo possui movimento em forma de L. Sempre dois deslocamentos em
         * uma direção e um na outra. Única peça do jogo que pode "pular"
         * outras.
         */
        Cavalo,
        /**
         * Peão possui primeiro movimento duplo, restante deslocamento somente
         * nas conlunas e sua tomada e na diagonal.
         */
        Peao,
        /**
         * Rainha é a combinação da Torre em conjunto com o Bispo (possui o
         * movimento combinado de ambos).
         */
        Rainha,
        /**
         * Rei é a peça mais importante do jogo (embora possua papel
         * "burocrático"), se move somente uma casa por vez em qualquer direção.
         */
        Rei,
        /**
         * Torre possui movimentos somente na horizontal ou vertical.
         */
        Torre;
    }
    /**
     * Adiciona handler para o evento de movimento desta peça.
     *
     * @param onMovimentoHandler
     *            Handler a ser adicionado a fila de notificação caso ocorra o
     *            movimento desta peça.
     */
    void addOnMovimentoHandler(IMovimentoHandler onMovimentoHandler);
    /**
     * Adiciona handler para o evento de tomada de peças.
     *
     * @param onTomadaHandler
     *            Handler a ser adicionado a fila de notificação caso ocorra
     *            tomada desta peça.
     */
    void addOnTomadaHandler(ITomadaHandler onTomadaHandler);
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
     * Move esta peça de acordo com as coordenadas de destino e origem da
     * jogada.
     *
     * @param jogada
     *            Objeto para fornecer informações das coordenadas da jogada.
     *
     * @param outra
     *            Caso a jogada seja uma tomada de peça, este objeto deve ser
     *            diferente de <code>null</code>.
     *
     * @throws ChessException
     *             Caso algum erro ocorra.
     *
     * @see IPeca#mover(int, int)
     */
    void mover(IJogada jogada, Peca outra) throws ChessException;
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
     * Retorna se o movimento da jogada é horizontal ou vertical.
     *
     * @param destI
     *            Linha de destino do movimento.
     *
     * @param destJ
     *            Coluna de destino do movimento.
     *
     * @return <code>true</code> caso o movimento seja horizontal ou vertical,
     *         <code>false</code> caso contrário.
     */
    boolean movimentoHorizVert(int destI, int destJ);
    /**
     * Retorna se o movimento da jogada é vertical.
     *
     * @param destI
     *            Linha de destino do movimento.
     *
     * @param destJ
     *            Coluna de destino do movimento.
     *
     * @return <code>true</code> caso o movimento seja considerado vertial,
     *         <code>false</code> caso contrário.
     */
    boolean movimentoVertical(int destI, int destJ);
    /**
     * @param i
     *            Nova linha desta peça.
     */
    void setI(int i);
    /**
     * @param j
     *            Nova coluna desta peça.
     */
    void setJ(int j);
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
     * @throws ChessException
     *             Caso o movimento seja inválido esta exceção será jogada.
     */
    void validarJogada(int destI, int destJ) throws ChessException;
}