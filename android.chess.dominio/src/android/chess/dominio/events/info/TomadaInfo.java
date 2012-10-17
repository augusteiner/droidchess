package android.chess.dominio.events.info;

import android.chess.dominio.events.info.interfaces.ITomadaInfo;
import android.chess.dominio.pecas.interfaces.IPeca;

/**
 * @author augusteiner
 *
 */
public class TomadaInfo extends MovimentoInfo implements ITomadaInfo {
    private IPeca dest;
    private IPeca orig;

    /**
     * @param orig
     * @param dest
     */
    public TomadaInfo(IPeca orig, IPeca dest) {
        super(orig, dest.getI(), dest.getJ());

        this.orig = orig;
        this.dest = dest;
    }

    /**
     * @param parent
     */
    public TomadaInfo(ITomadaInfo parent) {
        this(parent.getOrig(), parent.getDest());
    }

    /*
     * (non-Javadoc)
     *
     * @see android.chess.dominio.interfaces.IEventoTomada#getDest()
     */
    @Override
    public IPeca getDest() {
        return dest;
    }

    /*
     * (non-Javadoc)
     *
     * @see android.chess.dominio.interfaces.IEventoTomada#getOrig()
     */
    @Override
    public IPeca getOrig() {
        return orig;
    }
}
