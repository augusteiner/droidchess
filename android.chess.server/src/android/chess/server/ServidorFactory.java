package android.chess.server;

import android.chess.server.impl.Servidor;

/**
 * @author augusteiner
 *
 */
public class ServidorFactory {
    /**
     * @author augusteiner
     *
     */
    private static class Holder {
        /**
         *
         */
        private static final Servidor PADRAO = new Servidor();
    }
    /**
     * @return
     */
    public static Servidor getPadrao() {
        return Holder.PADRAO;
    }
}
