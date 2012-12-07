/**
 *
 */
package android.chess.server.exceptions;

/**
 * @author augusteiner
 *
 */
public class ConexaoException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = -1647875996448965975L;
    /**
     * @param cause
     */
    public ConexaoException(Throwable cause) {
        super("Falha ao se conectar ao servidor da aplicação,"
            + " por favor tente novamente após alguns instantes."
            + " Caso o problema persista entre em contato conosco.", cause);
    }
}
