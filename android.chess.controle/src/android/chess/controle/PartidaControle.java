package android.chess.controle;

import android.chess.dominio.Partida;
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
     * @param peca
     * @param origX
     * @param origY
     * @param destX
     * @param destY
     */
    public void jogada(IPeca peca, int destX, int destY) {
        try {
            partida.jogada(peca, destX, destY);
        } catch (Exception e) {
            // getMensageiro().alerta(e.getMessage());

            e.printStackTrace();
        }
    }
}
