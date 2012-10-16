package android.chess.test;

public class App {
    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @throws Exception
     *
     */
    private static void run() throws Exception {
        Test[] tests = new Test[]{
            new Jogada(),
        // new EventTest()
        };

        for (Test t : tests) {
            t.run();
        }
    }
}
