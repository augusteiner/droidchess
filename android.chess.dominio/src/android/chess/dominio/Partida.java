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
import android.chess.dominio.interfaces.IJogador;
import android.chess.dominio.interfaces.IPartida;
import android.chess.dominio.interfaces.ITabuleiro;
import android.chess.dominio.interfaces.IUsuario;
import android.chess.dominio.pecas.interfaces.IPeca.Cor;

/**
 * @author augusteiner
 *
 */
public class Partida implements IPartida, Serializable {
    /**
	 *
	 */
    private int id;
    /**
	 *
	 */
    private IJogador jogador1;

    /**
	 *
	 */
    private IJogador jogador2;

    /**
     *
     */
    private transient ITabuleiro tabuleiro;

    /**
	 *
	 */
    private Cor vencedor;

    /**
     *
     */
    private static final long serialVersionUID = -5291020267269476271L;

    /**
     * @param j1
     * @param j2
     */
    public Partida(IJogador j1, IJogador j2) {
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
    public int getId() {
        return id;
    }

    /**
     * @return
     */
    public IJogador getJogador1() {
        return jogador1;
    }

    /**
     * @return
     */
    public IJogador getJogador2() {
        return jogador2;
    }

    /**
     * @return
     */
    public IJogador[] getJogadores() {
        return new IJogador[]{
            jogador1, jogador2
        };
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

    public IJogador getVencedor() {
        return getJogadores()[vencedor.ordinal()];
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
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param jogador1
     */
    public void setJogador1(IJogador jogador1) {
        this.jogador1 = jogador1;
    }

    /**
     * @param jogador2
     */
    public void setJogador2(IJogador jogador2) {
        this.jogador2 = jogador2;
    }

    /**
     * @param vencedorIdx
     */
    public void setVencedor(IJogador jogador) {
        this.vencedor = jogador.getCor();
    }

    /**
     *
     */
    private void initJogadores(IJogador j1, IJogador j2) {
        j1.setCor(Cor.Branca);
        j2.setCor(Cor.Preta);

        jogador1 = j1;
        jogador2 = j2;
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