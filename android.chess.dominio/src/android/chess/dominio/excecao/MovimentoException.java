package android.chess.dominio.excecao;

/**
 * @author augusteiner
 *
 */
public abstract class MovimentoException extends ChessException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     * @param message
     *
     * @param cause
     */
    public MovimentoException(String message, Throwable cause) {
        super(message, cause);
    }

}
