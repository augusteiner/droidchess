/**
 *
 */
package android.chess.dominio;

import static java.lang.Math.abs;

import java.util.Iterator;

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
    private IPeca[][] pecas;
    private Jogador[] jogadores;

    /**
     * Inicializa a matriz de peças deste tabuleiro.
     */
    public Tabuleiro() {
        pecas = new Peca[8][8];

        initPecas();
    }

    /**
     * @return
     */
    public Iterator<IPeca> getPecas() {
        return new Iterator<IPeca>() {
            private int i = 0;
            private int j = -1;
            /*
             * (non-Javadoc)
             *
             * @see java.util.Iterator#hasNext()
             */
            @Override
            public boolean hasNext() {
                // TODO Auto-generated method stub
                return i < pecas.length || j < pecas[0].length;
            }

            /*
             * (non-Javadoc)
             *
             * @see java.util.Iterator#next()
             */
            @Override
            public IPeca next() {
                j++;

                if (j > 7) {
                    i++;
                    j = 0;
                }

                return pecas[i][j];
            }

            /*
             * (non-Javadoc)
             *
             * @see java.util.Iterator#remove()
             */
            @Override
            public void remove() {
                // TODO Auto-generated method stub

            }
        };
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

            linha[1] = new Cavalo(cor);
            linha[6] = new Cavalo(cor);

            linha[2] = new Bispo(cor);
            linha[5] = new Bispo(cor);

            linha[3] = new Rainha(cor);
            linha[4] = new Rei(cor);

            for (int j = 0; j < 8; j++) {
                pecas[abs(fLinha - 1)][j] = new Peao(cor);
            }

            fLinha = 7;
        }

        for (int i = 0, j; i < 2; i++) {
            for (j = 0; j < 8; j++) {
                pecas[i][j].set(i, j);

                pecas[7 - i][j].set(7 - i, j);
            }
        }
    }

    /**
     * @param cor
     */
    private void marcarPonto(IPeca peca) {
        // Índice do jogador a marcar pontos.
        int jIndex = abs(peca.getCor().compareTo(Cor.Branca));

        // partida.marcar(jIndex);
        // jogadores[jIndex].marcar();
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
            case Rei :
                // Checar condições de cheque e cheque mate
                break;
            case Rainha :
                break;
            case Peao :
                ok = movimentoDiagonal(jogada);

                break;
            case Torre :
                ok = !movimentoDiagonal(jogada) && movimentoHorizVert(jogada);

                break;
            case Cavalo :
            default :
                // throw new MovimentoInvalido(peca);
        }

        // TODO O set para null e depois dest para peça deve ser feito dentro do
        // método tomar(...) ?
        pecas[peca.getX()][peca.getY()] = null;

        jogada.realizar();

        pecas[jogada.getDestX()][jogada.getDestY()] = peca;

        tomar(jogada);
    }

    /**
     * Retorna se o movimento da jogada está sendo realizado nas diagonais.
     *
     * @param jogada
     *            Jogada cujo movimento está sendo testado.
     *
     * @return {@link Boolean} True caso o movimento seja na diagonal, False
     *         caso contrário.
     */
    private boolean movimentoDiagonal(Jogada jogada) {
        int origX = jogada.getPeca().getX();
        int origY = jogada.getPeca().getY();

        int destX = jogada.getDestX();
        int destY = jogada.getDestY();

        return abs(origX - destX) == abs(origY - destY);
    }

    /**
     * Retorna se o movimento da jogada é horizontal.
     *
     * @param jogada
     *            Jogada a ser testado o tipo de movimento.
     *
     * @return {@link Boolean} True caso o movimento seja na horizontal, False
     *         caso contrário.
     */
    private boolean movimentoHorizontal(Jogada jogada) {
        return jogada.getPeca().getY() == jogada.getDestY();
    }

    /**
     * Retorna se o movimento da jogada é vertical ou horizontal.
     *
     * @param jogada
     *            Jogada a ser testado o tipo de movimento.
     *
     * @return {@link Boolean} True caso o movimento seja horizontal ou vertical
     *         e False caso contrário.
     */
    private boolean movimentoHorizVert(Jogada jogada) {
        return movimentoHorizontal(jogada) || movimentoVertical(jogada);
    }

    /**
     * Retorna se o movimento da jogada é vertical.
     *
     * @param jogada
     *            Jogada a ser testado o tipo de movimento.
     *
     *
     * @return {@link Boolean} True caso o movimento seja na vertical, False
     *         caso contrário.
     */
    private boolean movimentoVertical(Jogada jogada) {
        return jogada.getPeca().getX() == jogada.getDestX();
    }

    /**
     *
     */
    protected void onTomada(IPeca peca, IPeca outra) {
        marcarPonto(peca);
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
     * @param jogada
     * @throws PecaNaoEncontrada
     */
    private void tomar(Jogada jogada) {
        IPeca outra = peca(jogada);

        if (outra != null)
            onTomada(jogada.getPeca(), outra);
    }
}