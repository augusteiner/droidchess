package android.chess.dominio.pecas.handlers;

import android.chess.dominio.interfaces.IEventoTomada;
import android.chess.dominio.interfaces.IPeca;

/**
 * @author augusteiner
 * 
 */
public class EventoTomada implements IEventoTomada {
    private IPeca dest;
    private IPeca orig;

    /**
     * @param parent
     */
    public EventoTomada(IEventoTomada parent) {
        this(parent.getOrig(), parent.getDest());
    }

    /**
     * @param orig
     * @param dest
     */
    public EventoTomada(IPeca orig, IPeca dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.chess.dominio.interfaces.IEventoTomada#getDest()
     */
    public IPeca getDest() {
        return dest;
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.chess.dominio.interfaces.IEventoTomada#getOrig()
     */
    public IPeca getOrig() {
        return orig;
    }
}
