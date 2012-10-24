package android.chess.test;

import android.chess.controle.PartidaControle;

/**
 * @author augusteiner
 *
 */
public class Cliente extends Test {
    /**
     *
     */
    public Cliente() {

    }
    /*
     * (non-Javadoc)
     *
     * @see android.chess.test.Test#run()
     */
    @Override
    public void run() throws Exception {
        PartidaControle c = new PartidaControle();

        c.novaPartida();
    }
}
