/**
 *
 */
package android.chess.dominio.excecao;

/**
 * @author augusteiner
 *
 */
public class PecaNaoEncontrada extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    public PecaNaoEncontrada() {
        this(null);
    }

    /**
     * @param cause
     */
    public PecaNaoEncontrada(Throwable cause) {
        super("Peca não encontrada na posição informada.", cause);
        // TODO Auto-generated constructor stub
    }
}
