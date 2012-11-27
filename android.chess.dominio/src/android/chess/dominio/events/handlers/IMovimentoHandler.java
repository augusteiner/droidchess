package android.chess.dominio.events.handlers;

import android.chess.dominio.events.info.interfaces.IMovimentoInfo;
import android.chess.dominio.excecao.ChessException;

/**
 * @author augusteiner
 * 
 */
public interface IMovimentoHandler {

    /**
     * @param info
     * @throws Exception
     */
    void onMovimento(Object sender, IMovimentoInfo info) throws ChessException;
}