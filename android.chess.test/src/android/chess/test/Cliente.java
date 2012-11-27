package android.chess.test;

import android.chess.controle.PartidaControle;
import android.chess.controle.UsuarioControle;

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
        UsuarioControle jogadaCtrl = new UsuarioControle();
        jogadaCtrl.autenticar("dev", "123456");

        PartidaControle partidaCtrl = new PartidaControle();

        partidaCtrl.novaPartida();

        partidaCtrl.getTabuleiro().print(System.out);
    }
}
