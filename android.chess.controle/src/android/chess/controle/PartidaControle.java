package android.chess.controle;

import android.chess.dominio.Partida;
import android.chess.dominio.Tabuleiro;
import android.chess.dominio.excecao.ChessException;
import android.chess.dominio.pecas.interfaces.IPeca.Cor;

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
        novaPartida();
    }

    /**
     * @return
     */
    public Tabuleiro getTabuleiro() {
        return partida.getTabuleiro();
    }

    /**
     * @return
     */
    public Cor getTurno() {
        return partida.getTurno();
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

    /**
     * @todo Criar evento para repassar a UI?
     *
     * @todo Deve requisitar à aplicação servidora uma nova partida.
     * @todo Implementar escolha/convite de adversário.
     */
    public void novaPartida() {
        partida = new Partida();
    }
}