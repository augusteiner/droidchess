package android.chess.test;

import android.chess.dominio.Partida;
import android.chess.dominio.excecao.MovimentoInvalido;
import android.chess.dominio.excecao.PecaNaoEncontrada;

public class Application {

    /**
     * @param args
     */
    public static void main(String[] args) {

        try {
            testJogadas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testJogadas() throws MovimentoInvalido, PecaNaoEncontrada {
        Partida p = new Partida();

        p.jogada(1, 0, 2, 0);
    }
}
