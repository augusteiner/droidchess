package android.chess.dominio.pecas.handlers;

import android.chess.dominio.interfaces.IMovimentoInfo;
import android.chess.dominio.interfaces.IPeca;

/**
 * @author augusteiner
 *
 */
public class EventoMover implements IMovimentoInfo {
    private int destI;
    private int destJ;
    protected IPeca alvo;

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
    @Override
    public IPeca getAlvo() {
        return alvo;
    }

    /**
     * @return
     */
    @Override
    public int getDestI() {
        return destI;
    }

    /**
     * @return
     */
    @Override
    public int getDestJ() {
        return destJ;
    }
}