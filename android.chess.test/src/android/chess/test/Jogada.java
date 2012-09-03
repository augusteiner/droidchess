package android.chess.test;

import java.util.Iterator;
import java.util.logging.Logger;

import javax.swing.DebugGraphics;

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
     * @return
     */
    public static Partida getPartida() {
        if (partida == null)
            partida = new Partida();

        return partida;
    }

    /**
     * Realiza uma jogada no tabuleiro da partida atual e imprime-o logo após.
     *
     * @param origX
     * @param origY
     * @param destX
     * @param destY
     * @throws JogadaInvalida
     * @throws PecaNaoEncontrada
     */
    public static void jogada(int origX, int origY, int destX, int destY)
            throws JogadaInvalida, PecaNaoEncontrada {
        Partida p = getPartida();

        p.jogada(origX, origY, destX, destY);

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
     * Realiza o teste com algumas jogadas
     *
     * @throws MovimentoInvalido
     * @throws PecaNaoEncontrada
     */
    private static void test() throws JogadaInvalida, PecaNaoEncontrada {
//        peao();
//        cavalo();
        bispo();
    }
}
