package android.chess.dominio.events.info.interfaces;

import android.chess.dominio.pecas.interfaces.IPeca;


/**
 * @author augusteiner
 * 
 */
public interface ITomadaInfo {

    /**
     * @return
     */
    public IPeca getDest();

    /**
     * @return
     */
    public IPeca getOrig();
}
