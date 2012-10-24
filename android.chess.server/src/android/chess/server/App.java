package android.chess.server;

import android.chess.server.impl.Servidor;

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
            Servidor.getInstancia().servir();
        } catch (Exception e) {
            e.printStackTrace();

            System.exit(-1);
        }
    }

}
