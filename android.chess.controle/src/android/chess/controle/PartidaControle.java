package android.chess.controle;

import android.chess.dominio.Partida;
import android.chess.dominio.Tabuleiro;
import android.chess.dominio.excecao.JogadaException;
import android.chess.dominio.excecao.PecaNaoEncontrada;
import android.chess.dominio.interfaces.IPeca;

/**
 * @author augusteiner
 *
 */
public class PartidaControle {
    private Partida partida;
    /**
     *
     */
    public PartidaControle() {
        partida = new Partida();
    }

    /**
     * @return
     */
    public Tabuleiro getTabuleiro() {
        return partida.getTabuleiro();
    }

    public void mover(int origI, int origJ, int destI, int destJ)
        throws JogadaException, PecaNaoEncontrada {
        partida.jogada(origI, origJ, destI, destJ);
    }
}
