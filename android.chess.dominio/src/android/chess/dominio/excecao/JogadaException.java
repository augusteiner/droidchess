/**
 *
 */
package android.chess.dominio.excecao;

import android.chess.dominio.interfaces.IJogada;

/**
 * Exceção a ser lançada caso a jogada em questão não seja válida.
 *
 * @author augusteiner
 */
public class JogadaException extends MovimentoException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    public JogadaException(IJogada jogada) {
        this(jogada, null);
    }

    /**
     * @param jogada
     * @param cause
     */
    public JogadaException(IJogada jogada, Throwable cause) {
        super(String.format("Jogada inválida %s.", jogada), cause);
    }

    /**
     * @param message
     * @param cause
     */
    protected JogadaException(String message, Throwable cause) {
        super(message, cause);
    }
}