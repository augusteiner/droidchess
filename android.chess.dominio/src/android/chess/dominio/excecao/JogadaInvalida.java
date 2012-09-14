/**
 *
 */
package android.chess.dominio.excecao;

import android.chess.dominio.Jogada;

/**
 * @author augusteiner
 *
 */
public class JogadaInvalida extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    public JogadaInvalida(Jogada jogada) {
        this(jogada, null);
    }

    /**
     * @param jogada
     * @param cause
     */
    public JogadaInvalida(Jogada jogada, Throwable cause) {
        super(String.format("Jogada inválida %s -> (%d:%d)", jogada.getPeca(),
                jogada.getDestI(), jogada.getDestJ()), cause);
    }
}
