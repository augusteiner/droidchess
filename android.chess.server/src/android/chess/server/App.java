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
            new Servidor().servir();
        } catch (Exception e) {
            System.err.println(e.getMessage());

            System.exit(-1);
        }
    }

}
