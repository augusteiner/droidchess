package android.chess.server;

import java.io.IOException;

/**
 * @author augusteiner
 *
 */
public class App {

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            new Server().serve();
        } catch (IOException e) {
            System.exit(-1);
        }
    }

}
