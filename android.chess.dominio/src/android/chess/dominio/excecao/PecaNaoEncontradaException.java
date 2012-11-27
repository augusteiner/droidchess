/**
 *
 */
package android.chess.dominio.excecao;

/**
 * @author augusteiner
 * 
 */
public class PecaNaoEncontradaException extends ChessException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    public PecaNaoEncontradaException() {
        this(null);
    }

    /**
     * @param cause
     */
    public PecaNaoEncontradaException(Throwable cause) {
        super("Peca não encontrada na posição informada.", cause);
        // TODO Auto-generated constructor stub
    }
}
