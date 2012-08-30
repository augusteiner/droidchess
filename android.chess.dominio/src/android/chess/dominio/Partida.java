/**
 *
 */
package android.chess.dominio;

import android.chess.dominio.excecao.MovimentoInvalido;
import android.chess.dominio.excecao.PecaNaoEncontrada;
import android.chess.dominio.interfaces.IPeca;

/**
 * @author augusteiner
 *
 */
public class Partida {
    private Tabuleiro tabuleiro;
    private Jogador[] jogadores;

    /**
     *
     */
    public Partida() {
        this(new Jogador(), new Jogador());

        tabuleiro = new Tabuleiro();
    }

    /**
     * @param j1
     * @param j2
     */
    public Partida(Jogador j1, Jogador j2) {
        initJogadores(j1, j2);
    }

    /**
     * @return
     */
    public Jogador[] getJogadores() {
        return jogadores;
    }

    /**
     * Retorna o tabuleiro desta partida.
     *
     * @return {@link Tabuleiro}
     */
    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    /**
     *
     */
    private void initJogadores(Jogador j1, Jogador j2) {
        jogadores = new Jogador[2];

        jogadores[0] = j1;
        jogadores[1] = j2;
    }

    /**
     * @param origX
     * @param origY
     * @param destX
     * @param destY
     * @throws MovimentoInvalido
     * @throws PecaNaoEncontrada
     */
    public void jogada(int origX, int origY, int destX, int destY)
            throws MovimentoInvalido, PecaNaoEncontrada {
        IPeca orig = tabuleiro.peca(origX, origY);

        tabuleiro.mover(orig, destX, destY);
    }
}
