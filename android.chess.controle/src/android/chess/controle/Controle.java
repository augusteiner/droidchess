package android.chess.controle;

/**
 * @author augusteiner
 *
 * @param <T>
 */
public abstract class Controle<T> {
    /**
     *
     */
    public Controle() {

    }
    /**
     * @return
     */
    public abstract T getControlado();
}
