package android.chess.test;

import java.util.Iterator;

import android.chess.dominio.Partida;
import android.chess.dominio.Tabuleiro;
import android.chess.dominio.excecao.MovimentoInvalido;
import android.chess.dominio.excecao.PecaNaoEncontrada;
import android.chess.dominio.interfaces.IPeca;

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

    /**
     * Realiza o teste com algumas jogadas
     *
     * @throws MovimentoInvalido
     * @throws PecaNaoEncontrada
     */
    private static void testJogadas() throws MovimentoInvalido, PecaNaoEncontrada {
        Partida p = new Partida();

        //Jogada simples entre dois peões.
        p.jogada(1, 0, 3, 0);
        p.jogada(6, 1, 4, 1);
        p.jogada(3, 0, 4, 1);

        printTabuleiro(p.getTabuleiro());
    }

    /**
     * Imprime uma representação gráfica do tabuleiro dado.
     *
     * @param tabuleiro
     * Tabuleiro a ser impresso.
     */
    private static void printTabuleiro(Tabuleiro tabuleiro)
    {
        Iterator<IPeca> pecas = tabuleiro.getPecas();
        IPeca peca = null;

        for (int i = 0, j; i < 8; i++)
        {
            System.out.print("\n|");

            for (j = 0; j < 8; j++)
            {
                peca = pecas.next();

                if (peca != null)
                    System.out.print(peca.getClass().getSimpleName().substring(0, 2));
                else
                    System.out.print("  ");

                System.out.print("|");
            }
        }
    }
}
