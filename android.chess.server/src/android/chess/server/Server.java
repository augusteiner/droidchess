package android.chess.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author augusteiner
 *
 */
public class Server extends ServerSocket {

    // FIXME Colocar configurações em arquivo .xml?
    /**
     *
     */
    public static final String address = "127.0.0.1";
    /**
     *
     */
    public static final int port = 9666;
    /**
     * @return
     * @throws IOException
     */
    public static Socket newSocket() throws IOException {
        return new Socket(address, port);
    }

    /**
     * @throws IOException
     */
    public Server() throws IOException {
        super(port);
    }

    /**
     * @throws IOException
     */
    public void serve() throws IOException {
        while (true) {
            new ServerThread(this).start();
        }
    }
}