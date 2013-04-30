package android.chess.dominio.events.handlers;

import android.chess.dominio.events.args.interfaces.IMovimentoArgs;
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
    void onMovimento(Object sender, IMovimentoArgs info) throws ChessException;
}