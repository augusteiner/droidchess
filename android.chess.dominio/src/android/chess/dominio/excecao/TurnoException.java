package android.chess.dominio.excecao;

import android.chess.dominio.pecas.interfaces.IPeca.Cor;

/**
 * @author augusteiner
 * 
 */
public class TurnoException extends MovimentoException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * @param atual
     */
    public TurnoException(Cor atual) {
        this(String.format("O turno é da %s", atual.toString()), null);
    }

    /**
     * @param message
     * @param cause
     */
    public TurnoException(String message, Throwable cause) {
        super(message, cause);
    }
}
