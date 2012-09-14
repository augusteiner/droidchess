package android.chess.test;

import java.util.Iterator;

import android.chess.dominio.Partida;
import android.chess.dominio.Tabuleiro;
import android.chess.dominio.excecao.JogadaInvalida;
import android.chess.dominio.excecao.MovimentoInvalido;
import android.chess.dominio.excecao.PecaNaoEncontrada;
import android.chess.dominio.interfaces.IPeca;

public class Jogada {

    private static Partida partida;

    /**
     * Testa jogadas com um bispo.
     *
     * @throws PecaNaoEncontrada
     * @throws JogadaInvalida
     *
     */
    private static void bispo() throws JogadaInvalida, PecaNaoEncontrada {
        novaPartida();

        // Removendo os peões do caminho.
        jogada(1, 6, 3, 6);

        // Movendo o bispo.
        jogada(0, 5, 2, 7);
    }

    /**
     * Testa jogadas com um cavalo.
     *
     * @throws JogadaInvalida
     * @throws PecaNaoEncontrada
     */
    private static void cavalo() throws JogadaInvalida, PecaNaoEncontrada {

        novaPartida();

        System.out.println("Testando movimentos do cavalo.");

        jogada(0, 6, 2, 5);
        jogada(2, 5, 3, 7);
        jogada(3, 7, 2, 5);

        System.out.println("OK!");
    }

    /**
     * @deprecated
     *
     * @return
     */
    @Deprecated
    public static Partida getPartida() {
        if (partida == null)
            partida = new Partida();

        return partida;
    }

    /**
     * Realiza uma jogada no tabuleiro da partida atual e imprime-o logo após.
     *
     * @param origI
     * @param origJ
     * @param destI
     * @param destJ
     * @throws JogadaInvalida
     * @throws PecaNaoEncontrada
     */
    public static void jogada(int origI, int origJ, int destI, int destJ)
            throws JogadaInvalida, PecaNaoEncontrada {
        Partida p = getPartida();

        p.jogada(origI, origJ, destI, destJ);

        print(p.getTabuleiro());
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        try {
            test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Partida do zero para reinicializar posições possivelmente alteradas.
     */
    private static void novaPartida() {
        partida = new Partida();
    }

    /**
     * Jogada simples entre dois peões.
     *
     * @throws PecaNaoEncontrada
     * @throws JogadaInvalida
     */
    private static void peao() throws JogadaInvalida, PecaNaoEncontrada {
        jogada(1, 0, 3, 0);
        jogada(6, 1, 4, 1);
        jogada(3, 0, 4, 1);
    }
    /**
     * Imprime uma representação gráfica do tabuleiro dado.
     *
     * @param tabuleiro
     *            Tabuleiro a ser impresso.
     */
    private static void print(Tabuleiro tabuleiro) {
        Iterator<IPeca> pecas = tabuleiro.getPecas();
        IPeca peca = null;

        for (int i = 0, j; i < 8; i++) {
            System.out.print("\n|");

            for (j = 0; j < 8; j++) {
                peca = pecas.next();

                if (peca != null)
                    System.out.print(peca.getClass().getSimpleName()
                            .substring(0, 2));
                else
                    System.out.print("  ");

                System.out.print("|");
            }
        }

        System.out.println("\n");
    }

    /**
     * @throws JogadaInvalida
     * @throws PecaNaoEncontrada
     */
    private static void rainha() throws JogadaInvalida, PecaNaoEncontrada {
        jogada(0, 3, 3, 6);
    }

    /**
     * Realiza o teste com algumas jogadas
     *
     * @throws MovimentoInvalido
     * @throws PecaNaoEncontrada
     */
    private static void test() throws JogadaInvalida, PecaNaoEncontrada {
        peao();
        cavalo();
        bispo();
        rainha();
    }
}
