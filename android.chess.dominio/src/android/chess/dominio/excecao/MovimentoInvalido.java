package android.chess.dominio.excecao;

import android.chess.dominio.interfaces.IPeca;

/**
 * Exceção a ser jogada quando um movimento
 * de uma peça não for válido.
 *
 * @author augusteiner
 *
 * @since 0.1
 */
public class MovimentoInvalido extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public MovimentoInvalido(IPeca peca) {
        super(
            String.format(
                "Peça \'%s\' não pode ser movida.",
                peca
            )
        );
        // TODO Auto-generated constructor stub
    }

    public MovimentoInvalido(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public MovimentoInvalido(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public MovimentoInvalido(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

}
