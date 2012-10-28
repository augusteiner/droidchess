package android.chess.test;

import android.chess.controle.JogadorControle;
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
        JogadorControle jogadaCtrl = new JogadorControle();
        jogadaCtrl.autenticar("dev", "123456");

        PartidaControle partidaCtrl = new PartidaControle(jogadaCtrl);

        partidaCtrl.novaPartida();
    }
}
