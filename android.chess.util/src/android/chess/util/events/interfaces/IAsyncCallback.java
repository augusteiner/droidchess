package android.chess.util.events.interfaces;

/**
 * @author augusteiner
 *
 * @param <T>
 */
public interface IAsyncCallback<T> {
    /**
     * Invoca de forma assíncrona corpo do método.
     *
     * @return
     */
    public void invoke(T arg);
}