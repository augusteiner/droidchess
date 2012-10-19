package android.chess.dominio;

import static java.lang.Math.abs;

import java.util.Iterator;

import android.chess.dominio.events.handlers.IAntesPromocaoHandler;
import android.chess.dominio.events.handlers.IDepoisPromocaoHandler;
import android.chess.dominio.events.handlers.IMovimentoHandler;
import android.chess.dominio.events.handlers.ITomadaHandler;
import android.chess.dominio.events.info.interfaces.IMovimentoInfo;
import android.chess.dominio.events.info.interfaces.IPromocaoInfo;
import android.chess.dominio.events.info.interfaces.ITomadaInfo;
import android.chess.dominio.excecao.ChessException;
import android.chess.dominio.excecao.MovimentoException;
import android.chess.dominio.excecao.JogadaException;
import android.chess.dominio.excecao.MovimentoInvalidoException;
import android.chess.dominio.excecao.PecaNaoEncontradaException;
import android.chess.dominio.excecao.PromocaoException;
import android.chess.dominio.excecao.TurnoException;
import android.chess.dominio.interfaces.IJogada;
import android.chess.dominio.iterators.MatrixIterator;
import android.chess.dominio.iterators.PecaIterator;
import android.chess.dominio.pecas.Bispo;
import android.chess.dominio.pecas.Cavalo;
import android.chess.dominio.pecas.Peao;
import android.chess.dominio.pecas.Rainha;
import android.chess.dominio.pecas.Rei;
import android.chess.dominio.pecas.Torre;
import android.chess.dominio.pecas.interfaces.IPeca;
import android.chess.dominio.pecas.interfaces.IPeca.Cor;
import android.chess.dominio.pecas.interfaces.IPeca.Tipo;

/**
 * Classe de domínio para prover validação de jogadas e modelagem geral do jogo
 * de xadrez.
 *
 * @author augusteiner
 */
public class Tabuleiro
    implements
        IAntesPromocaoHandler,
        IDepoisPromocaoHandler,
        IMovimentoHandler,
        ITomadaHandler {

    private Cor atual;
    private IPeca[][] pecas;

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
     * Retorna um iterator para as peças deste tabuleiro garantindo que as
     * mesmas não sejam nulas.
     *
     * @return {@link Iterator} para as peças deste tabuleiro.
     */
    public Iterator<IPeca> getPecas() {
        return new PecaIterator(pecas);
    }

    /**
     * @return
     */
    public Cor getTurno() {
        return atual;
    }

    /**
     *
     */
    private void initPecas() {
        int fLinha = 0;
        Peao atual;

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
                atual = new Peao(cor);
                atual.addOnAntesPromocaoHandler(this);
                atual.addOnDepoisPromocaoHandler(this);

                pecas[abs(fLinha - 1)][j] = atual;
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

        Iterator<IPeca> pecas = getPecas();

        while (pecas.hasNext()) {
            pecas.next().addOnMovimentoHandler(this);
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
    @SuppressWarnings("unused")
    @Deprecated
    private void marcarPonto(IPeca peca) {
        // Índice do jogador a marcar pontos.

        int jIndex = abs(peca.getCor().compareTo(Cor.Branca));

        // partida.marcar(jIndex);
        // jogadores[jIndex].marcar();
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
     * @throws PecaNaoEncontradaException
     *
     * @throws MovimentoInvalidoException
     *             Caso o movimento seja considerado inválido.
     *
     * @todo Utilizar factory (possívelmente Partida) para instanciar jogada.
     */
    public void mover(int origI, int origJ, int destI, int destJ)
        throws ChessException {
        Jogada jogada = new Jogada(origI, origJ, destI, destJ);
        IPeca peca = peca(jogada);

        if (peca.getCor().outra() == atual)
            throw new TurnoException(atual);

        try {
            realizar(jogada);

            atual = atual.outra();
        } catch (JogadaException e) {
            throw e;
        } catch (MovimentoException e) {
            throw new JogadaException(jogada, e);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * android.chess.dominio.interfaces.handlers.IPromocaoHandler#onAntesPromocao
     * (android.chess.dominio.interfaces.IPromocaoInfo)
     */
    @Override
    public void onAntesPromocao(IPromocaoInfo info) throws ChessException {

        if (info.getAlvo().getTipo() != Tipo.Peao) {
            throw new PromocaoException(info);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * android.chess.dominio.interfaces.handlers.IPromocaoHandler#onDepoisPromocao
     * (android.chess.dominio.interfaces.IPromocaoInfo)
     */
    @Override
    public void onDepoisPromocao(IPromocaoInfo info) throws ChessException {

        pecas[info.getDestI()][info.getDestJ()] = info.getAlvo();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * android.chess.dominio.interfaces.handlers.IMovimentoHandler#onMovimento
     * (android.chess.dominio.interfaces.IEventoMover)
     */
    @Override
    public void onMovimento(Object sender, IMovimentoInfo info)
        throws ChessException {
        IPeca peca = info.getAlvo();

        // Refletindo alterações da jogada no tabuleiro.
        pecas[info.getDestI()][info.getDestJ()] = peca;
        pecas[peca.getI()][peca.getJ()] = null;
    }

    @Override
    public void onTomada(ITomadaInfo evento) throws MovimentoException {

    }

    /**
     * @param jogada
     * @return
     * @throws PecaNaoEncontradaException
     */
    private IPeca outra(IJogada jogada) {
        try {
            return peca(jogada.getDestI(), jogada.getDestJ());
        } catch (PecaNaoEncontradaException e) {
            return null;
        }
    }

    /**
     * @param jogada
     * @return
     * @throws PecaNaoEncontradaException
     */
    private IPeca peca(IJogada jogada) throws ChessException {
        return peca(jogada.getOrigI(), jogada.getOrigJ());
    }

    /**
     * @param i
     * @param j
     * @return
     */
    private IPeca peca(int i, int j) throws PecaNaoEncontradaException {
        if (i < 0 || j < 0 || i > 7 || j > 7)
            throw new PecaNaoEncontradaException();

        Peca peca = (Peca) pecas[i][j];

        if (peca == null)
            throw new PecaNaoEncontradaException();

        return peca;
    }

    /**
     * Verifica se há alguma peça no caminho da jogada a ser realizada. Método
     * só deve ser chamado após as validações do movimento da peça.
     *
     * @param jogada
     *
     * @return A peça no caminho da jogada ou <code>null</code> caso não haja
     *         peça.
     */
    private IPeca pecaNoCaminho(IJogada jogada) {

        int iinc = (int) jogada.sentidoI();
        int jinc = (int) jogada.sentidoJ();

        int i = jogada.getOrigI() + iinc;
        int j = jogada.getOrigJ() + jinc;

        while ((i != jogada.getDestI() || j != jogada.getDestJ())
            && pecas[i][j] == null) {
            i += iinc;
            j += jinc;
        }

        return pecas[i][j];
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
    /**
     * @param jogada
     * @throws ChessException
     */
    private void realizar(Jogada jogada) throws ChessException {
        IPeca peca;

        try {
            peca = peca(jogada);
        } catch (PecaNaoEncontradaException e) {
            throw new JogadaException(jogada);
        }

        // peca.validar(jogada);

        IPeca outra = outra(jogada);

        boolean jogadaInvalida = false;
        boolean checarPecaNoCaminho = true;

        // TODO Checar condições de cheque e cheque mate
        switch (peca.getTipo()) {
            case Rei :
            break;
            case Rainha :
            break;
            case Peao :
            break;
            case Torre :
            break;
            // Validação por parte do tabuleiro vazia para o caso do Cavalo.
            case Cavalo :
                checarPecaNoCaminho = false;
            break;
            default :
            break;
        }

        if (checarPecaNoCaminho) {
            jogadaInvalida = pecaNoCaminho(jogada) != outra;
        }

        if (jogadaInvalida) {
            throw new JogadaException(jogada);
        }

        peca.mover(jogada, (Peca) outra);
    }
}