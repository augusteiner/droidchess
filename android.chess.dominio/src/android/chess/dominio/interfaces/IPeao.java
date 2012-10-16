/**
 *
 */
package android.chess.dominio.interfaces;

import android.chess.dominio.interfaces.handlers.IAntesPromocaoHandler;
import android.chess.dominio.pecas.handlers.IDepoisPromocaoHandler;

/**
 * @author augusteiner
 *
 */
public interface IPeao extends IPeca {

    /**
     * @param onPromocaoHandler
     */
    void addOnAntesPromocaoHandler(IAntesPromocaoHandler onPromocaoHandler);

    void addOnDepoisPromocaoHandler(
        IDepoisPromocaoHandler onDepoisPromocaoHandler);
}