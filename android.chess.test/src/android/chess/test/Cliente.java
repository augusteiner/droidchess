/**
 *
 */
package android.chess.test;

import android.chess.controle.Controle;
import android.chess.controle.PartidaControle;
import android.chess.dominio.interfaces.IPartida;

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
        Controle<IPartida> c = new PartidaControle();
    }
}
