/**
 *
 */
package android.chess.dominio;

import static java.lang.Math.abs;

import java.util.Iterator;

import android.chess.dominio.excecao.JogadaInvalida;
import android.chess.dominio.excecao.MovimentoInvalido;
import android.chess.dominio.excecao.PecaNaoEncontrada;
import android.chess.dominio.interfaces.IJogada;
import android.chess.dominio.interfaces.IPeca;
import android.chess.dominio.interfaces.IPeca.Cor;
import android.chess.dominio.iterators.MatrixIterator;
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

    // private Jogador[] jogadores;
    // private Partida partida;

    /**
     * Inicializa a matriz de peças deste tabuleiro.
     */
    public Tabuleiro() {
        pecas = new Peca[8][8];

        initTabuleiro();
    }

    /**
     * @return
     */
    public Iterator<IPeca> getPecas() {
        return new MatrixIterator<IPeca>(pecas);
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
                pecas[i][j].set(i, j);

                pecas[7 - i][j].set(7 - i, j);
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

        @SuppressWarnings("unused")
        int jIndex = abs(peca.getCor().compareTo(Cor.Branca));

        // partida.marcar(jIndex);
        // jogadores[jIndex].marcar();
    }

    /**
     * @param peca
     * @param destI
     * @param destJ
     * @throws MovimentoInvalido
     */
    public void mover(IPeca peca, int destI, int destJ) throws JogadaInvalida {

        realizar(new Jogada(peca, destI, destJ));
    }

    /**
     *
     */
    protected void onTomada(IPeca peca, IPeca outra) {
        marcarPonto(peca);
    }

    /**
     * @param jogada
     * @return
     * @throws PecaNaoEncontrada
     */
    private IPeca outra(IJogada jogada) {
        try {
            return outra(jogada.getDestI(), jogada.getDestJ());
        } catch (PecaNaoEncontrada e) {
            return null;
        }
    }

    /**
     * @param i
     * @param j
     * @return
     */
    IPeca outra(int i, int j) throws PecaNaoEncontrada {
        if (i < 0 || j < 0 || i > 7 || j > 7)
            throw new PecaNaoEncontrada();

        IPeca peca = pecas[i][j];

        if (peca == null)
            throw new PecaNaoEncontrada();

        return peca;
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
    private boolean pecaNoCaminho(IJogada jogada) {
        if (jogada.movimentoDiagonal())
            return pecaNoCaminhoDiagonal(jogada);
        else
            return pecaNoCaminhoHorizVert(jogada);
    }

    /**
     *
     * @param jogada
     *
     * @return
     */
    private boolean pecaNoCaminhoDiagonal(IJogada jogada) {
        int inc = jogada.sentido();

        int i = jogada.getPeca().getI() + inc;
        int j = jogada.getPeca().getJ() + inc;

        while (i != jogada.getDestI() && j != jogada.getDestJ()
                && pecas[i][j] == null) {
            i += inc;
            j += inc;
        }

        return pecas[i][j] != null;
    }

    /**
     *
     * @param jogada
     *
     * @return Um <code>boolean</code> sinalizando se há peça no caminho
     *         Horizontal ou Diagonal da jogada.
     *
     * @todo Implementar
     */
    private boolean pecaNoCaminhoHorizVert(IJogada jogada) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * Realiza operações para realizar o movimento de uma peça da jogada neste
     * tabuleiro.
     *
     * @param jogada
     *            Jogada em questão.
     *
     * @throws MovimentoInvalido
     *             Caso o movimento da peça não seja válido.
     *
     * @throws JogadaInvalida
     *             Caso a jogada em questão não seja válida.
     */
    private void realizar(IJogada jogada) throws MovimentoInvalido,
            JogadaInvalida {

        IPeca peca = jogada.getPeca();

        peca.validar(jogada);

        IPeca outra = outra(jogada);

        boolean ok = (outra == null || peca.getCor() != outra.getCor())
                && !pecaNoCaminho(jogada);

        // Peças que tem o movimento limitado
        // devido a outras peças na trajetória até o destino.
        switch (peca.getTipo()) {
            case Rei :
                // TODO Checar condições de cheque e cheque mate
                break;
            case Rainha :

                break;
            case Peao :

                break;
            case Torre :

                break;
            case Cavalo :

                break;
            default :
        }

        if (!ok) {
            throw new JogadaInvalida(jogada);
        }

        int i = peca.getI();
        int j = peca.getJ();

        jogada.realizar();

        // TODO O set para null e depois dest para peça deve ser feito dentro do
        // método tomar(...) ?
        tomar(jogada);

        // Refletindo alterações da jogada no tabuleiro.
        pecas[i][j] = null;
        pecas[jogada.getDestI()][jogada.getDestJ()] = peca;
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
    private void realizar(Jogada jogada) throws JogadaInvalida {

        try {
            realizar((IJogada) jogada);
        } catch (MovimentoInvalido e) {
            throw new JogadaInvalida(jogada, e);
        }
    }

    /**
     * @param jogada
     * @throws PecaNaoEncontrada
     */
    private void tomar(IJogada jogada) {
        IPeca outra = outra(jogada);

        if (outra != null && jogada.getPeca().getCor() != outra.getCor())
            onTomada(jogada.getPeca(), outra);
    }
}