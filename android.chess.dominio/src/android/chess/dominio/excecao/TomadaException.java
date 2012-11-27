package android.chess.dominio.excecao;

import android.chess.dominio.interfaces.IJogada;
import android.chess.dominio.pecas.interfaces.IPeca;

/**
 * @author augusteiner
 * 
 */
public class TomadaException extends JogadaException {

    /**
     *
     */
    private static final long serialVersionUID = -4359969638851532784L;

    /**
     * @param jogada
     * @param cause
     */
    public TomadaException(IJogada jogada, Throwable cause) {
        super(jogada, cause);
    }

    /**
     * @param orig
     * @param dest
     */
    public TomadaException(IPeca orig, IPeca dest) {
        this(orig, dest, null);
    }

    public TomadaException(IPeca orig, IPeca dest, Throwable cause) {
        super(String.format("Tomada de '%s' por '%s' inválida.", dest, orig),
            cause);
    }
}