package android.chess.dominio.events.args.interfaces;

import android.chess.dominio.pecas.interfaces.IPeca;

/**
 * @author augusteiner
 * 
 */
public interface ITomadaArgs {

    /**
     * @return
     */
    public IPeca getDest();

    /**
     * @return
     */
    public IPeca getOrig();
}
