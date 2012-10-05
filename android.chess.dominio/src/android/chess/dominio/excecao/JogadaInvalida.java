/**
 *
 */
package android.chess.dominio.excecao;

import android.chess.dominio.interfaces.IJogada;

/**
 * @author augusteiner
 * 
 */
public class JogadaInvalida extends JogadaException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    public JogadaInvalida(IJogada jogada) {
        this(jogada, null);
    }

    /**
     * @param jogada
     * @param cause
     */
    public JogadaInvalida(IJogada jogada, Throwable cause) {
        super(String.format("Jogada inválida %s -> (%d:%d)", jogada.getPeca(),
            jogada.getDestI(), jogada.getDestJ()), cause);
    }
}