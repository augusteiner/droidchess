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

    /**
     * @param peca
     * @param destI
     * @param destJ
     * @throws PecaNaoEncontrada
     *
     * @throws Exception
     */
    public void mover(IPeca peca, int destI, int destJ) throws JogadaException, PecaNaoEncontrada {
        partida.jogada(peca, destI, destJ);
    }
}
