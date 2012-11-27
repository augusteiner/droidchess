/**
 *
 */
package android.chess.visao.exceptions;

import android.chess.dominio.excecao.ChessException;

/**
 * @author augusteiner
 * 
 */
public class InicializacaoException extends ChessException {

    /**
     *
     */
    private static final long serialVersionUID = 8313020673559144510L;

    /**
     * @param string
     * @param cause
     */
    public InicializacaoException(Throwable cause) {
        super(String.format("Falha na inicialização."), cause);
    }
}
