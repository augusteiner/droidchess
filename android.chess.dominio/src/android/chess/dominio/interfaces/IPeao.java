/**
 *
 */
package android.chess.dominio.interfaces;

import android.chess.dominio.interfaces.handlers.IPromocaoHandler;

/**
 * @author augusteiner
 *
 */
public interface IPeao extends IPeca {

    /**
     * @param onPromocaoHandler
     */
    void addOnPromocaoHandler(IPromocaoHandler onPromocaoHandler);
}