package android.chess.controle;

import android.chess.dominio.Partida;
import android.chess.dominio.Tabuleiro;
import android.chess.dominio.excecao.JogadaInvalida;
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
     *
     * @throws Exception
     */
    public void mover(IPeca peca, int destI, int destJ) throws Exception {
        try {
            partida.jogada(peca, destI, destJ);
        } catch (Exception e) {
            throw e;
        }
    }
}
