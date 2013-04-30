package android.chess.dominio.events.args;

import android.chess.dominio.events.args.interfaces.ITomadaArgs;
import android.chess.dominio.pecas.interfaces.IPeca;

/**
 * @author augusteiner
 * 
 */
public class TomadaArgs extends MovimentoArgs implements ITomadaArgs {
    private IPeca dest;
    private IPeca orig;

    /**
     * @param orig
     * @param dest
     */
    public TomadaArgs(IPeca orig, IPeca dest) {
        super(orig, dest.getI(), dest.getJ());

        this.orig = orig;
        this.dest = dest;
    }

    /**
     * @param parent
     */
    public TomadaArgs(ITomadaArgs parent) {
        this(parent.getOrig(), parent.getDest());
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.chess.dominio.events.info.interfaces.ITomadaInfo#getDest()
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
