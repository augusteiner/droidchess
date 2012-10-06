/**
 *
 */
package android.chess.dominio;

import static java.lang.Math.abs;

import java.util.Iterator;

import android.chess.dominio.excecao.JogadaException;
import android.chess.dominio.excecao.JogadaInvalida;
import android.chess.dominio.excecao.MovimentoInvalido;
import android.chess.dominio.excecao.PecaNaoEncontrada;
import android.chess.dominio.excecao.VezInvalida;
import android.chess.dominio.interfaces.IJogada;
import android.chess.dominio.interfaces.IPeca;
import android.chess.dominio.interfaces.IPeca.Cor;
import android.chess.dominio.iterators.MatrixIterator;
import android.chess.dominio.iterators.PecaIterator;
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
/**
 * @author augusteiner
 *
 */
public class Tabuleiro {

    private Cor atual;
    private Peca[][] pecas;

    // private Jogador[] jogadores;
    // private Partida partida;

    /**
     * Inicializa a matriz de peças deste tabuleiro.
     */
    public Tabuleiro() {
        pecas = new Peca[8][8];

        initTabuleiro();

        atual = Cor.Branca;
    }

    /**
     * @return
     */
    public Iterator<IPeca> getMatrizPecas() {
        return new MatrixIterator<IPeca>(pecas);
    }

    /**
     * @return
     */
    public Iterator<IPeca> getPecas() {
        return new PecaIterator(pecas);
    }

    /**
     *
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
                pecas[i][j].setI(i);
                pecas[i][j].setJ(j);

                pecas[7 - i][j].setI(7 - i);
                pecas[7 - i][j].setJ(j);
            }
        }
    }

    /**
     * Inicia peças nas suas devidas posições.
     */
    private void initTabuleiro() {
        initPecas();
    }

    /**
     * @deprecated
     *
     * @param cor
     */
    @Deprecated
    private void marcarPonto(IPeca peca) {
        // Índice do jogador a marcar pontos.

        int jIndex = abs(peca.getCor().compareTo(Cor.Branca));

        // partida.marcar(jIndex);
        // jogadores[jIndex].marcar();
    }

    /**
     * @param origI
     * @param origJ
     * @param destI
     * @param destJ
     * @throws JogadaException
     * @throws PecaNaoEncontrada
     */
    public void mover(int origI, int origJ, int destI, int destJ)
        throws JogadaException, PecaNaoEncontrada {
        mover(peca(origI, origJ), destI, destJ);

    }

    /**
     * Move uma peça nessa instância de tabuleiro para um ponto de destino
     * representado por (destI, destJ).
     *
     * @param peca
     *            Peça a ser movida.
     *
     * @param destI
     *            Linha de destino para a peça.
     *
     * @param destJ
     *            Coluna de destino para a peça.
     * @throws PecaNaoEncontrada
     *
     * @throws MovimentoInvalido
     *             Caso o movimento seja considerado inválido.
     *
     * @todo Utilizar factory (possívelmente Partida) para instanciar jogada.
     */
    private void mover(Peca peca, int destI, int destJ) throws JogadaException {

        if (peca.getCor().outra() == atual)
            throw new VezInvalida(atual);

        Jogada jogada = new Jogada(peca, destI, destJ);

        try {
            realizar(peca, jogada);

            atual = atual.outra();
        } catch (JogadaInvalida e) {
            throw e;
        } catch (JogadaException e) {
            throw new JogadaInvalida(jogada, e);
        }
    }

    /**
     * @param jogada
     * @param outra
     * @throws JogadaException
     */
    protected void onTomada(IJogada jogada, Peca outra) throws JogadaException {
        jogada.tomar(outra);

        // marcarPonto(peca);
    }

    /**
     * @param jogada
     * @return
     * @throws PecaNaoEncontrada
     */
    private Peca outra(IJogada jogada) {
        try {
            return peca(jogada.getDestI(), jogada.getDestJ());
        } catch (PecaNaoEncontrada e) {
            return null;
        }
    }

    /**
     * Verifica se há alguma peça no caminho da jogada a ser realizada. Método
     * só deve ser chamado após as validações do movimento da peça.
     *
     * @param jogada
     *
     * @return <code>true</code> caso haja peça no caminho da jogada,
     *         <code>false</code> caso contrário.
     */
    // private boolean pecaNoCaminho(IJogada jogada) {
    // if (jogada.movimentoDiagonal())
    // return pecaNoCaminho(jogada);
    // else if (jogada.movimentoHorizontal())
    // return pecaNoCaminhoHorizontal(jogada);
    // else if (jogada.movimentoVertical())
    // return pecaNoCaminhoVertical(jogada);
    // else
    // return false;
    // }

    /**
     * @param i
     * @param j
     * @return
     */
    private Peca peca(int i, int j) throws PecaNaoEncontrada {
        if (i < 0 || j < 0 || i > 7 || j > 7)
            throw new PecaNaoEncontrada();

        Peca peca = pecas[i][j];

        if (peca == null)
            throw new PecaNaoEncontrada();

        return peca;
    }

    /**
     * @param jogada
     * @return
     * @throws PecaNaoEncontrada
     */
    private Peca peca(Jogada jogada) throws PecaNaoEncontrada {
        IPeca peca = jogada.getPeca();

        return peca(peca.getI(), peca.getJ());
    }

    /**
     *
     * @param jogada
     *
     * @return
     */
    private boolean pecaNoCaminho(IJogada jogada) {

        IPeca peca = jogada.getPeca();

        int iinc = (int) jogada.sentidoI();
        int jinc = (int) jogada.sentidoJ();

        int i = peca.getI() + iinc;
        int j = peca.getJ() + jinc;

        while ((i != jogada.getDestI() || j != jogada.getDestJ())
            && pecas[i][j] == null) {
            i += iinc;
            j += jinc;
        }

        return pecas[i][j] != null;
    }

    /**
     * Valida o movimento desta peça de acordo com as coordenadas de destino
     * informadas e realiza o movimento da mesma.
     *
     * @param jogada
     *
     * @throws MovimentoInvalido
     *
     * @todo Adicionar suporte a jogadas especiais.
     */
    /**
     * Realiza operações para realizar o movimento de uma peça da jogada neste
     * tabuleiro.
     *
     * @param jogada
     *            Jogada em questão.
     * @throws PecaNaoEncontrada
     *
     * @throws MovimentoInvalido
     *             Caso o movimento da peça não seja válido.
     *
     * @throws JogadaInvalida
     *             Caso a jogada em questão não seja válida.
     */
    private void realizar(Peca peca, Jogada jogada) throws JogadaException {

        // peca.validar(jogada);

        Peca outra = outra(jogada);

        boolean isOutraOponente = outra == null
            || peca.getCor() != outra.getCor();

        boolean ok = false;

        // TODO Checar condições de cheque e cheque mate

        // Peças que tem o movimento limitado
        // devido a outras peças na trajetória até o destino.
        switch (peca.getTipo()) {
            case Rei :
            break;
            case Rainha :
            break;
            case Peao :
            break;
            case Torre :
            break;
            case Cavalo :
                // Validação por parte do tabuleiro vazia para o caso do Cavalo.
                ok = outra == null || outra.getCor() != peca.getCor();
            break;
            default :
            break;
        }

        boolean hasPecaNoCaminho = !ok && pecaNoCaminho(jogada);

        ok = isOutraOponente || !hasPecaNoCaminho;

        if (!ok) {
            throw new JogadaInvalida(jogada);
        }

        int i = peca.getI();
        int j = peca.getJ();

        jogada.realizar();

        if (outra != null)
            tomar(jogada, outra);

        // Refletindo alterações da jogada no tabuleiro.
        pecas[i][j] = null;
        pecas[jogada.getDestI()][jogada.getDestJ()] = peca;
    }

    /**
     * @param jogada
     * @param outra
     *
     * @throws MovimentoInvalido
     *
     * @throws PecaNaoEncontrada
     */
    private void tomar(Jogada jogada, Peca outra) throws JogadaException {
        if (jogada.getPeca().getCor() != outra.getCor())
            onTomada(jogada, outra);
    }
}