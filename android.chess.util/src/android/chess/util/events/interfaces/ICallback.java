package android.chess.util.events.interfaces;

/**
 * @author augusteiner
 *
 * @param <TResult>
 */
public interface ICallback<TArg> {
    /**
     * Invoca corpo do método de forma síncrona.
     *
     * @return
     * @throws Exception
     */
    public void invoke(TArg arg) throws Exception;
}
