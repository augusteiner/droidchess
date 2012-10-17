package android.chess.dominio.interfaces.handlers;

import android.chess.dominio.excecao.ChessException;
import android.chess.dominio.interfaces.IMovimentoInfo;

/**
 * @author augusteiner
 *
 */
public interface IMovimentoHandler  {

    /**
     * @param info
     * @throws Exception
     */
    void onMovimento(Object sender, IMovimentoInfo info) throws ChessException;
}