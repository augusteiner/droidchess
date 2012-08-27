/**
 *
 */
package android.chess.dominio;

import static java.lang.Math.abs;
import android.chess.dominio.excecao.MovimentoInvalido;
import android.chess.dominio.excecao.PecaNaoEncontrada;
import android.chess.dominio.interfaces.IPeca;
import android.chess.dominio.interfaces.IPeca.Cor;
import android.chess.dominio.pecas.Bispo;
import android.chess.dominio.pecas.Cavalo;
import android.chess.dominio.pecas.Peao;
import android.chess.dominio.pecas.Peca;
import android.chess.dominio.pecas.Rainha;
import android.chess.dominio.pecas.Rei;
import android.chess.dominio.pecas.Torre;

/**
 * Classe de domínio para prover validação de jogadas e modelagem geral do jogo
 * de xadrez.
 *
 * @author augusteiner
 */
public class Tabuleiro {
    private Peca[][] pecas;
    private Jogador[] jogadores;

    /**
     * Inicializa a matriz de peças deste tabuleiro.
     */
    public Tabuleiro() {
        pecas = new Peca[8][8];

        initPecas();
    }

    /**
     * Inicia peças nas suas devidas posições.
     */
    private void initPecas() {
        int fLinha = 0;

        for (Cor cor : Cor.values()) {
            IPeca[] linha = pecas[fLinha];

            linha[0] = new Torre(cor);
            linha[7] = new Torre(cor);

            linha[1] = new Bispo(cor);
            linha[6] = new Bispo(cor);

            linha[2] = new Cavalo(cor);
            linha[5] = new Cavalo(cor);

            linha[abs(fLinha - 3)] = new Rei(cor);
            linha[abs(fLinha - 4)] = new Rainha(cor);

            for (int j = 0; j < 8; j++) {
                pecas[abs(fLinha - 1)][j] = new Peao(cor);
            }

            fLinha = 7;
        }
    }

    /**
     * @param peca
     * @param destX
     * @param destY
     * @throws MovimentoInvalido
     */
    public void mover(IPeca peca, int destX, int destY)
            throws MovimentoInvalido {
        mover(new Jogada(peca, destX, destY));
    }

    /**
     * Valida o movimento desta peça de acordo com as coordenadas de destino
     * informadas e realiza o movimento da mesma.
     *
     * @param x
     *            Coordenada x de destino.
     *
     * @param y
     *            Coordenada y de destino.
     *
     * @todo Adicionar suporte a jogadas especiais
     */
    /**
     * @param jogada
     * @throws MovimentoInvalido
     */
    private void mover(Jogada jogada) throws MovimentoInvalido {
        boolean ok = true;

        IPeca peca = jogada.getPeca();

        // Peças que tem o movimento limitado
        // devido a outras peças na trajetória até o destino.
        switch (peca.getTipo()) {
        case Rei:
            // Checar condições de cheque e cheque mate
            break;
        case Rainha:
            break;
        case Peao:
            ok = movimentoDiagonal(jogada);

            break;
        case Torre:
            ok = !movimentoDiagonal(jogada) && movimentoHorizVert(jogada);

            break;
        default:
            throw new MovimentoInvalido(peca);
        }

        jogada.realizar();

        tomar(jogada);
    }

    /**
     * @param jogada
     * @return
     * @throws PecaNaoEncontrada
     */
    private IPeca peca(Jogada jogada) {
        try {
            return peca(jogada.getDestX(), jogada.getDestY());
        } catch (PecaNaoEncontrada e) {
            return null;
        }
    }

    /**
     * @param x
     * @param y
     * @return
     */
    IPeca peca(int x, int y) throws PecaNaoEncontrada {
        return pecas[x][y];
    }

    /**
     * @param jogada
     * @throws PecaNaoEncontrada
     */
    private void tomar(Jogada jogada) {
        IPeca outra = peca(jogada);

        if (outra != null)
            onTomada(jogada.getPeca(), outra);
    }

    /**
     *
     */
    protected void onTomada(IPeca peca, IPeca outra) {
        marcarPonto(peca.getCor());
    }

    /**
     * @param cor
     */
    private void marcarPonto(IPeca.Cor cor) {
        // Índice do jogador a marcar pontos.
        int jIndex = abs(cor.compareTo(Cor.Branca));

        // partida.marcar(jIndex);
        // jogadores[jIndex].marcar();
    }

    /**
     * @param peca
     * @param destX
     * @param destY
     * @return
     */
    private boolean movimentoDiagonal(Jogada jogada) {
        return false;
    }

    /**
     * @param peca
     * @param destX
     * @param destY
     * @return
     */
    private boolean movimentoHorizVert(Jogada jogada) {
        return false;
    }
}