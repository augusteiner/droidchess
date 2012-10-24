package android.chess.controle;

/**
 * @author augusteiner
 *
 * @param <T>
 *
 * @deprecated
 */
@Deprecated
public class Controle<T> {
    /**
     *
     */
    T controlado;
    /**
     * @param controlado
     */
    public Controle(T controlado) {
        this.controlado = controlado;
    }
    /**
     * @return
     */
    public T getControlado() {
        return controlado;
    }
}
