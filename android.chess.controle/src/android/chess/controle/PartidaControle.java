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
     * @param origX
     * @param origY
     * @param destX
     * @param destY
     *
     * @throws Exception
     */
    public void jogada(IPeca peca, int destX, int destY) throws Exception {
        try {
            partida.jogada(peca, destX, destY);
        } catch (JogadaInvalida e) {
            throw e;
        } catch (Exception e) {
            // getMensageiro().alerta(e.getMessage());

            throw e;
        }
    }
}
