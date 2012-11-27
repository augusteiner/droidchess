package android.chess.dominio.events.handlers;

import android.chess.dominio.events.info.interfaces.IPromocaoInfo;
import android.chess.dominio.excecao.ChessException;

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