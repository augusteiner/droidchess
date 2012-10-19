/**
 *
 */
package android.chess.visao.handlers;

import android.chess.dominio.events.info.TomadaInfo;
import android.chess.dominio.events.info.interfaces.ITomadaInfo;
import android.chess.visao.PecaAbstrata;

/**
 * @author augusteiner
 *
 */
public class EventoTomada
    extends
        TomadaInfo {
    private PecaAbstrata peca;

    /**
     *
     */
    public EventoTomada(ITomadaInfo parent, PecaAbstrata peca) {
        super(parent);

        this.peca = peca;
    }

    /**
     * Retorna a view para a peça sendo movida na UI.
     *
     * @return Peça sendo movida.
     */
    public PecaAbstrata getPeca() {
        return peca;
    }
}
