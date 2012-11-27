/**
 *
 */
package android.chess.client;

import java.io.IOException;

import javax.net.ssl.SSLSocketFactory;

import android.chess.server.impl.Servidor;

/**
 * @author augusteiner
 * 
 */
public class ClienteSSL extends Cliente {
    /**
     * @author augusteiner
     * 
     */
    private static class Holder {
        /**
         *
         */
        private static final ClienteSSL INSTANCIA = new ClienteSSL();
    }
    /**
     *
     */
    public ClienteSSL() {
        super(false);

        try {
            socket = SSLSocketFactory.getDefault().createSocket(
                Servidor.address, Servidor.port);

            initStreams();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * @return
     */
    public static ClienteSSL getInstancia() {
        return Holder.INSTANCIA;
    }
}
