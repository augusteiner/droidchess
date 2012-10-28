package android.chess.client;

/**
 * @author augusteiner
 *
 */
public class ClienteFactory {
    /**
     * @author augusteiner
     *
     */
    private static class Holder {
        /**
         *
         */
        private static final Cliente PADRAO = new Cliente();
    }
    /**
     * @return
     */
    public static Cliente getPadrao() {
        return Holder.PADRAO;
    }
}
