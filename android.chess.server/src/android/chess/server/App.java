package android.chess.server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author augusteiner
 *
 */
public class App {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ServerSocket server = null;

        try {
            server = new Server(9666);

            while (true) {
                server.accept();
            }
        } catch (IOException e) {
            System.exit(-1);
        }
    }

}
