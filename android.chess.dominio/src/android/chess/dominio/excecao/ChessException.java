package android.chess.dominio.excecao;

/**
 * @author augusteiner
 *
 */
public abstract class ChessException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * @param string
     * @param cause
     */
    public ChessException(String string, Throwable cause) {
        super(string, cause);
    }
}