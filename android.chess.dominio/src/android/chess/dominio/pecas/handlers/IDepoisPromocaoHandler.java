/**
 *
 */
package android.chess.dominio.pecas.handlers;

import android.chess.dominio.excecao.ChessException;
import android.chess.dominio.interfaces.IPromocaoInfo;

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