package android.chess.dominio.pecas.handlers;

import android.chess.dominio.interfaces.IPeca;

/**
 * @author augusteiner
 * 
 */
public class EventoMover {
    private int destI;
    private int destJ;
    private IPeca alvo;

    /**
     * @param alvo
     * @param destI
     * @param destJ
     */
    public EventoMover(IPeca alvo, int destI, int destJ) {
        this.alvo = alvo;

        this.destI = destI;
        this.destJ = destJ;
    }

    /**
     * @return
     */
    public IPeca getAlvo() {
        return alvo;
    }

    /**
     * @return
     */
    public int getDestI() {
        return destI;
    }

    /**
     * @return
     */
    public int getDestJ() {
        return destJ;
    }
}
