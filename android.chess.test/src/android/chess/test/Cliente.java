/**
 *
 */
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
    @SuppressWarnings("deprecation")
    @Override
    public void run() throws Exception {
        PartidaControle c = new PartidaControle();

        c.novaPartida();

        android.chess.client.Cliente.getInstancia().dispose();
    }
}
