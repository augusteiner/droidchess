/**
 *
 */
package android.chess.util.events.interfaces;

/**
 * Interface básica para manipulação de eventos.
 *
 * @author augusteiner
 */
public interface IEvent<I> {
    /**
     * Adiciona um handler a este evento.
     *
     * @param handler
     *            Handler a ser executado quando um evento for acionado.
     */
    void addHandler(IHandler<I> handler);

    /**
     * Aciona este evento notificando ao seus handlers.
     *
     * @param info
     *            Informação a ser repassada aos handlers deste evento.
     *
     * @throws Exception
     */
    public void raise(I info) throws Exception;
}
