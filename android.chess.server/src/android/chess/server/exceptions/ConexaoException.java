/**
 *
 */
package android.chess.server.exceptions;

import android.chess.dominio.excecao.ChessException;

/**
 * @author augusteiner
 *
 */
public class ConexaoException extends ChessException {
    /**
     *
     */
    private static final long serialVersionUID = -1647875996448965975L;
    /**
     * @param cause
     */
    public ConexaoException(Throwable cause) {
        super("Falha ao se conectar ao servidor,"
            + " por favor tente novamente em alguns instantes."
            + "\n\nCaso o problema persista entre em contato conosco.", cause);
    }
}
