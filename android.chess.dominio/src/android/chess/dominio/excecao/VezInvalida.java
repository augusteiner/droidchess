package android.chess.dominio.excecao;

import android.chess.dominio.interfaces.IPeca.Cor;

/**
 * @author augusteiner
 *
 */
public class VezInvalida extends JogadaException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * @param atual
     */
    public VezInvalida(Cor atual) {
        this(String.format("O turno é da %s", atual.toString()), null);
    }

    /**
     * @param message
     * @param cause
     */
    public VezInvalida(String message, Throwable cause) {
        super(message, cause);
    }
}
