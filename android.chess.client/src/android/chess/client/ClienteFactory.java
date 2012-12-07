package android.chess.client;

/**
 * @author augusteiner
 *
 */
public class ClienteFactory {
    /**
     * @return
     * @throws Exception
     */
    public static Cliente getPadrao() throws Exception {
        return Cliente.getInstancia();
    }
}
