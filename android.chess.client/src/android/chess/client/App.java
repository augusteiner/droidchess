package android.chess.client;

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
            new Client().request();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
