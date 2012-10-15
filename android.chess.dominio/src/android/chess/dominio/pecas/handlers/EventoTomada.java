package android.chess.dominio.pecas.handlers;

import android.chess.dominio.interfaces.ITomadaInfo;
import android.chess.dominio.interfaces.IPeca;

/**
 * @author augusteiner
 *
 */
public class EventoTomada extends EventoMover implements ITomadaInfo {
    private IPeca dest;
    private IPeca orig;

    /**
     * @param parent
     */
    public EventoTomada(ITomadaInfo parent) {
        this(parent.getOrig(), parent.getDest());
    }

    /**
     * @param orig
     * @param dest
     */
    public EventoTomada(IPeca orig, IPeca dest) {
        super(orig, dest.getI(), dest.getJ());

        this.orig = orig;
        this.dest = dest;
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
