package android.chess.dominio.events.args;

import android.chess.dominio.events.args.interfaces.IMovimentoArgs;
import android.chess.dominio.pecas.interfaces.IPeca;

/**
 * @author augusteiner
 * 
 */
public class MovimentoArgs implements IMovimentoArgs {
    private int destI;
    private int destJ;
    protected IPeca alvo;

    /**
     * @param alvo
     * @param destI
     * @param destJ
     */
    public MovimentoArgs(IPeca alvo, int destI, int destJ) {
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