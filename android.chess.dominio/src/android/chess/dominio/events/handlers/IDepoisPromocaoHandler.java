/**
 *
 */
package android.chess.dominio.events.handlers;

import android.chess.dominio.events.info.interfaces.IPromocaoInfo;
import android.chess.dominio.excecao.ChessException;

/**
 * @author augusteiner
 *
 */
public interface IDepoisPromocaoHandler {

    /**
     * Método a ser acionado depois de um evento de promoção ser acionado.
     *
     * @param info
     *            Informações acerca do evento.
     *
     * @throws ChessException
     */
    public void onDepoisPromocao(IPromocaoInfo info) throws ChessException;
}