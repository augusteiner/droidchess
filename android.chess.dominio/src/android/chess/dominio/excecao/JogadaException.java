package android.chess.dominio.excecao;

/**
 * @author augusteiner
 *
 */
public class JogadaException extends Exception {

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
    public JogadaException(String message, Throwable cause) {
        super(message, cause);
    }

}
