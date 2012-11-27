/**
 *
 */
package android.chess.controle.exceptions;

/**
 * @author augusteiner
 * 
 */
public class ExecucaoException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 7635546194098799357L;
    /**
     *
     */
    public ExecucaoException() {
        // TODO Auto-generated constructor stub
    }
    /**
     * @param message
     */
    public ExecucaoException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }
    /**
     * @param message
     * @param cause
     */
    public ExecucaoException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }
    /**
     * @param cause
     */
    public ExecucaoException(Throwable cause) {
        super(cause);
    }
}
