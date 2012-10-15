package android.chess.dominio.interfaces.handlers;

import android.chess.dominio.excecao.ChessException;
import android.chess.dominio.interfaces.IMovimentoInfo;
import android.chess.util.events.interfaces.IHandler;

/**
 * @author augusteiner
 *
 */
public interface IMovimentoHandler extends IHandler<IMovimentoInfo> {

    /**
     * @param info
     * @throws Exception
     */
    @Override
    void onMovimento(Object sender, IMovimentoInfo info) throws ChessException;
}