/**
 * 
 */
package android.chess.visao.handlers;

import android.chess.dominio.events.info.interfaces.ITomadaInfo;
import android.chess.visao.Peca;

/**
 * @author augusteiner
 * 
 */
public class EventoTomada
    extends
        android.chess.dominio.events.info.TomadaInfo {
    private Peca peca;

    /**
     * 
     */
    public EventoTomada(ITomadaInfo parent, Peca peca) {
        super(parent);

        this.peca = peca;
    }

    /**
     * Retorna a view para a peça sendo movida na UI.
     * 
     * @return Peça sendo movida.
     */
    public Peca getPeca() {
        return peca;
    }
}
