package android.chess.dominio.interfaces.handlers;

import android.chess.dominio.excecao.ChessException;
import android.chess.dominio.interfaces.IEventoPromocao;

/**
 * @author augusteiner
 * 
 */
public interface IPromocaoHandler {
    /**
     * @param evento
     * 
     * @throws ChessException
     */
    public void onAntesPromocao(IEventoPromocao evento) throws ChessException;

    /**
     * 
     * @param evento
     * 
     * @throws ChessException
     */
    public void onDepoisPromocao(IEventoPromocao evento) throws ChessException;
}
