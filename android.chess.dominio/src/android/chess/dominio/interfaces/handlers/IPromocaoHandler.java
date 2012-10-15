package android.chess.dominio.interfaces.handlers;

import android.chess.dominio.excecao.ChessException;
import android.chess.dominio.interfaces.IPromocaoInfo;

/**
 * @author augusteiner
 *
 */
public interface IPromocaoHandler extends IMovimentoHandler {
    /**
     * Método a ser acionado antes de um evento de promoção ser acionado.
     *
     * @param info
     *            Informações acerca do evento.
     *
     * @throws ChessException
     *             Caso o movimento não possa ser realizado.
     */
    public void onAntesPromocao(IPromocaoInfo info) throws ChessException;

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
