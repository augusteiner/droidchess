/**
 *
 */
package android.chess.dominio;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

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
public class Partida implements IPartida, Serializable {
    /**
     *
     */
    private Usuario[] jogadores;
    /**
     *
     */
    private transient ITabuleiro tabuleiro;
    /**
     *
     */
    private static final long serialVersionUID = -5291020267269476271L;
    /**
     * @param j1
     * @param j2
     */
    public Partida(Usuario j1, Usuario j2) {
        tabuleiro = new Tabuleiro();

        initJogadores(j1, j2);
    }

    /**
     *
     */
    Partida() {
        this(new Usuario(), new Usuario());

        tabuleiro = new Tabuleiro();
    }

    /**
     * @return
     */
    public Usuario[] getJogadores() {
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
    private void initJogadores(Usuario j1, Usuario j2) {
        jogadores = new Usuario[2];

        jogadores[0] = j1;
        jogadores[1] = j2;
    }
    /**
     * Inicializa propriedades transientes (como o tabuleiro desta partida) afim
     * de economizar networking.
     *
     * @param in
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void readObject(ObjectInputStream in) throws IOException,
        ClassNotFoundException {
        in.defaultReadObject();

        tabuleiro = new Tabuleiro();
    }
}