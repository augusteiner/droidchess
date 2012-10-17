/**
 *
 */
package android.chess.dominio.excecao;

import android.chess.dominio.interfaces.IJogada;

/**
 * @author augusteiner
 *
 */
public class JogadaInvalidaException extends JogadaException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    public JogadaInvalidaException(IJogada jogada) {
        this(jogada, null);
    }

    /**
     * @param jogada
     * @param cause
     */
    public JogadaInvalidaException(IJogada jogada, Throwable cause) {
        super(String.format("Jogada inválida %s", jogada), cause);
    }
}