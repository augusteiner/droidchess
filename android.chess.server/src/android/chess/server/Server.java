/**
 *
 */
package android.chess.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

/**
 * @author augusteiner
 *
 */
public class Server extends ServerSocket {

    /**
     * @throws IOException
     */
    public Server() throws IOException {

    }

    /**
     * @param port
     * @throws IOException
     */
    public Server(int port) throws IOException {
        super(port);
    }

    /**
     * @param port
     * @param backlog
     * @throws IOException
     */
    public Server(int port, int backlog) throws IOException {
        super(port, backlog);
    }

    /**
     * @param arg0
     * @param arg1
     * @param arg2
     * @throws IOException
     */
    public Server(int arg0, int arg1, InetAddress arg2) throws IOException {
        super(arg0, arg1, arg2);
    }

}
