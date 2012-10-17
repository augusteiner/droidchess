package android.chess.util.events.interfaces;

/**
 * Novo manipulador para a informação de um evento.
 *
 * @author augusteiner
 */
public interface IHandler<I> {
    /**
     * Manipula a informação repassada pelo evento.
     *
     * @param sender
     *            Quem enviou a requisição de manipulação.
     *
     * @param info
     *            Informações adicionais do evento.
     * @throws Exception
     */
    public void handle(Object sender, I info) throws Exception;
}
