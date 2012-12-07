package android.chess.test;

import android.chess.controle.PartidaControle;
import android.chess.controle.UsuarioControle;
import android.chess.controle.exceptions.ExecucaoException;
import android.chess.dominio.interfaces.IPartida;
import android.chess.dominio.interfaces.IUsuario;
import android.chess.util.events.interfaces.IAsyncCallback;

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
    public void run() throws Throwable {
        UsuarioControle jogadaCtrl = new UsuarioControle(null);

        jogadaCtrl.autenticar("dev", "123456", new IAsyncCallback<IUsuario>() {
            /**
             * @param usuario
             * @throws Exception
             */
            @Override
            public void invoke(IUsuario usuario) {
                PartidaControle partidaCtrl;
                try {
                    partidaCtrl = new PartidaControle();

                    partidaCtrl.novaPartida(new IAsyncCallback<IPartida>() {
                        /**
                         * @param partida
                         */
                        @Override
                        public void invoke(IPartida partida) {
                            partida.getTabuleiro().print(System.out);
                        }
                    });
                } catch (ExecucaoException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
