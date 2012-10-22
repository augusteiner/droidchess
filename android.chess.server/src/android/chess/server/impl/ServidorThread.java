/**
 *
 */
package android.chess.server.impl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Thread para permitir ao servidor tratar vários clientes.
 *
 * @author augusteiner
 */
public class ServidorThread extends Thread {

    private Socket client;
    private Servidor server;

    /**
     * Constrói a thread e inicia a recepção de conexões por parte do servidor.
     *
     * @param server
     *            Servidor ao qual a thread irá repassar as várias requisições.
     *
     * @throws IOException
     *             Exceção jogada pelo método accept de
     *             {@link ServerSocket#accept()}.
     */
    public ServidorThread(Servidor server) throws IOException {
        this.server = server;
        this.client = server.accept();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Thread#start()
     */
    @Override
    public void run() {
        try {
            server.servir(client);
        } catch (Exception e) {
            e.printStackTrace();

            // System.exit(-1);
        }
    }
}
