package android.chess;

import android.chess.test.Jogada;
import android.chess.test.Test;

public class App {
    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            run();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * @throws Exception
     * 
     */
    private static void run() throws Throwable {
        Test[] tests = new Test[] { new Jogada()
        // new Evento(),
        // new Cliente(),
        // new Persistencia()
        };

        for (Test t : tests) {
            t.run();
        }
    }
}
