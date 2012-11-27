package android.chess.server.impl;

import java.io.IOException;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

/**
 * @author augusteiner
 * 
 */
public class ServidorSSL extends Servidor {
    /**
     * @author augusteiner
     * 
     */
    private static class Holder {
        /**
         *
         */
        private static final ServidorSSL INSTANCIA = new ServidorSSL();
    }
    /**
     *
     */
    public ServidorSSL() {
        super(false);

        try {
            SSLServerSocket innerSocket = (SSLServerSocket) SSLServerSocketFactory
                .getDefault().createServerSocket(port);

            socket = innerSocket;
        } catch (IOException e) {
            e.printStackTrace();

            System.exit(1);
        }
    }
    /**
     * @return
     */
    public static ServidorSSL getInstancia() {
        return Holder.INSTANCIA;
    }
}
