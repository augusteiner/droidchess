package android.chess.server;

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
            ServidorFactory.getPadrao().servir();
        } catch (Exception e) {
            e.printStackTrace();

            System.exit(-1);
        }
    }

}