package android.chess.controle;

import android.chess.dominio.Partida;
import android.chess.dominio.Tabuleiro;
import android.chess.dominio.excecao.ChessException;

/**
 * @author augusteiner
 *
 */
public class PartidaControle {
    /**
     *
     */
    private Partida partida;

    /**
     *
     * @todo Adicionar jogadores como parametro.
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
     *
     * @param origI
     *
     * @param origJ
     *
     * @param destI
     *
     * @param destJ
     *
     * @throws ChessException
     */
    public void mover(int origI, int origJ, int destI, int destJ)
        throws ChessException {
        partida.jogada(origI, origJ, destI, destJ);
    }
}