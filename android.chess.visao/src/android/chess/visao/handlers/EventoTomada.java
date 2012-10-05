/**
 * 
 */
package android.chess.visao.handlers;

import android.chess.dominio.interfaces.IEventoTomada;
import android.chess.visao.Peca;

/**
 * @author augusteiner
 * 
 */
public class EventoTomada
    extends
        android.chess.dominio.pecas.handlers.EventoTomada {
    private Peca peca;

    /**
     * 
     */
    public EventoTomada(IEventoTomada parent, Peca peca) {
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
