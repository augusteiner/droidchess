/**
 *
 */
package android.chess.dominio;

import android.chess.dominio.excecao.ChessException;
import android.chess.dominio.excecao.JogadaException;
import android.chess.dominio.excecao.PecaNaoEncontradaException;
import android.chess.dominio.interfaces.IJogada;
import android.chess.dominio.interfaces.IPartida;
import android.chess.dominio.interfaces.ITabuleiro;
import android.chess.dominio.pecas.interfaces.IPeca.Cor;

/**
 * @author augusteiner
 *
 */
public class Partida implements IPartida {
    /**
     *
     */
    private Jogador[] jogadores;
    /**
     *
     */
    private ITabuleiro tabuleiro;
    /**
     *
     */
    private static final long serialVersionUID = -5291020267269476271L;
    /**
     * @param j1
     * @param j2
     */
    public Partida(Jogador j1, Jogador j2) {
        tabuleiro = new Tabuleiro();

        initJogadores(j1, j2);
    }

    /**
     *
     */
    Partida() {
        this(new Jogador("Jogador de teste #1"), new Jogador(
            "Jogador de teste #2"));

        tabuleiro = new Tabuleiro();
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
    @Override
    public ITabuleiro getTabuleiro() {
        return tabuleiro;
    }

    /**
     * @return
     */
    @Override
    public Cor getTurno() {
        return tabuleiro.getTurno();
    }

    /**
     *
     * @param origI
     *
     * @param origJ
     *
     * @param destI
     *
     * @param destJ
     *
     * @throws MovimentoInvalidoException
     *
     * @throws PecaNaoEncontradaException
     */
    @Override
    public void jogada(int origI, int origJ, int destI, int destJ)
        throws JogadaException {

        IJogada jogada = new Jogada(origI, origJ, destI, destJ);

        try {
            tabuleiro.mover(jogada);
        } catch (ChessException e) {

            throw new JogadaException(jogada, e);
        }
    }

    /**
     *
     */
    private void initJogadores(Jogador j1, Jogador j2) {
        jogadores = new Jogador[2];

        jogadores[0] = j1;
        jogadores[1] = j2;
    }
}