package android.chess.dominio.interfaces.handlers;

import android.chess.dominio.excecao.ChessException;
import android.chess.dominio.interfaces.IPromocaoInfo;

/**
 * @author augusteiner
 *
 */
public interface IAntesPromocaoHandler extends IMovimentoHandler {
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
}