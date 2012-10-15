package android.chess.test;

import java.util.Iterator;

import android.chess.dominio.Partida;
import android.chess.dominio.Tabuleiro;
import android.chess.dominio.excecao.ChessException;
import android.chess.dominio.excecao.JogadaException;
import android.chess.dominio.excecao.JogadaInvalida;
import android.chess.dominio.excecao.MovimentoInvalido;
import android.chess.dominio.excecao.PecaNaoEncontrada;
import android.chess.dominio.interfaces.IPeca;

/**
 * @author augusteiner
 *
 */
@SuppressWarnings("unused")
public class Jogada extends Test {
    private Partida partida;

    /**
     * Testa jogadas com um bispo.
     *
     * @throws PecaNaoEncontrada
     * @throws JogadaInvalida
     *
     */
    private void bispo() throws ChessException {
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
    private void cavalo() throws ChessException {

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
    public Partida getPartida() {
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
    public void jogada(int origI, int origJ, int destI, int destJ)
        throws ChessException {
        Partida p = getPartida();

        p.jogada(origI, origJ, destI, destJ);

        print(p.getTabuleiro());
    }

    /**
     * Partida do zero para reinicializar posições possivelmente alteradas.
     */
    private void novaPartida() {
        partida = new Partida();
    }

    /**
     * Jogada simples entre dois peões.
     *
     * @throws PecaNaoEncontrada
     * @throws JogadaInvalida
     */
    private void peao() throws ChessException {
        jogada(1, 0, 3, 0);
        jogada(6, 1, 4, 1);
        jogada(3, 0, 4, 1);
    }

    /**
     * @throws JogadaException
     * @throws PecaNaoEncontrada
     */
    private void peaoTorre() throws ChessException {
        jogada(6, 0, 4, 0);
        jogada(7, 0, 3, 0);
    }

    /**
     * Imprime uma representação gráfica do tabuleiro dado.
     *
     * @param tabuleiro
     *            Tabuleiro a ser impresso.
     */
    private void print(Tabuleiro tabuleiro) {
        Iterator<IPeca> pecas = tabuleiro.getMatrizPecas();
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
     * @throws ChessException
     */
    private void promocao() throws ChessException {
        jogada(1, 4, 3, 4);
        jogada(6, 3, 4, 3);

        jogada(3, 4, 4, 3);
        jogada(6, 4, 5, 4);

        jogada(4, 3, 5, 3);
        jogada(7, 3, 3, 7);

        jogada(5, 3, 6, 3);
        jogada(6, 0, 5, 0);

        jogada(6, 3, 7, 3);
        jogada(5, 0, 4, 0);

        jogada(7, 3, 3, 7);
    }

    /**
     * @throws JogadaInvalida
     * @throws PecaNaoEncontrada
     */
    private void rainha() throws ChessException {
        // Peão na frente da rainha.
        jogada(1, 4, 2, 4);
        jogada(0, 3, 2, 5);
        jogada(0, 4, 0, 3);
        jogada(2, 5, 2, 3);
    }

    /**
     * Realiza o teste com algumas jogadas
     *
     * @throws MovimentoInvalido
     * @throws PecaNaoEncontrada
     */
    @Override
    public void run() throws ChessException {
        // peao();
        // cavalo();
        // bispo();
        // rainha();
        // peaoTorre();
        // tentarMate();
        promocao();
    }

    /**
     * @throws ChessException
     */
    private void tentarMate() throws ChessException {
        // Saindo da frente da rainha branca.
        jogada(1, 4, 3, 4);
        // Liberando caminho para tentativa de xeque.
        jogada(6, 5, 4, 5);
        // Jogando a rainha
        jogada(0, 3, 4, 7);
        // Passando a vez para a branca.
        jogada(6, 0, 4, 0);

        try {
            // Tentando o xeque.
            jogada(4, 7, 7, 4);
        } catch (Exception e) {
            // Doing nothing.
        }

        jogada(4, 7, 5, 7);

        // Só reimprimindo tabuleiro.
        // print(getPartida().getTabuleiro());
    }
}
