/**
 *
 */
package android.chess.dominio.pecas.interfaces;

import android.chess.dominio.events.handlers.IAntesPromocaoHandler;
import android.chess.dominio.events.handlers.IDepoisPromocaoHandler;

/**
 * @author augusteiner
 *
 */
public interface IPeao extends IPeca {

    /**
     * @param handler
     */
    void addOnAntesPromocaoHandler(IAntesPromocaoHandler handler);

    /**
     * @param handler
     */
    void addOnDepoisPromocaoHandler(IDepoisPromocaoHandler handler);
}