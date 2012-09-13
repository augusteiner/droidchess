/**
 *
 */
package android.chess.dominio;

import static java.lang.Math.abs;

import java.util.Iterator;

import android.chess.dominio.excecao.JogadaInvalida;
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
    /**
     * @author augusteiner
     *
     */
    public class PecaIterator implements Iterator<IPeca> {
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
    }

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
        return new PecaIterator();
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
     * @param destX
     * @param destY
     * @throws MovimentoInvalido
     */
    public void mover(IPeca peca, int destX, int destY) throws JogadaInvalida {
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
    private void mover(Jogada jogada) throws JogadaInvalida {

        IPeca peca = jogada.getPeca();
        boolean ok = false;

        // Peças que tem o movimento limitado
        // devido a outras peças na trajetória até o destino.
        switch (peca.getTipo()) {
        case Rei :
            // Checar condições de cheque e cheque mate
            break;
        case Rainha :
            break;
        case Peao :
            ok = jogada.movimentoHorizontal();

            break;
        case Torre :
            ok = jogada.movimentoHorizDiag();

            break;
        case Cavalo :
        default :
        }

        ok = !pecaNoCaminho(jogada);

        IPeca outra = peca(jogada);

        ok &= outra == null || peca.getCor() != outra.getCor();

        if (!ok) {
            throw new JogadaInvalida(jogada);
        }

        int x = peca.getX();
        int y = peca.getY();

        jogada.realizar();

        // TODO O set para null e depois dest para peça deve ser feito dentro do
        // método tomar(...) ?
        tomar(jogada);

        // Refletindo alterações da jogada no tabuleiro.
        pecas[x][y] = null;
        pecas[jogada.getDestX()][jogada.getDestY()] = peca;
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
        if (x < 0 || y < 0 || x > 7 || y > 7)
            throw new PecaNaoEncontrada();

        IPeca peca = pecas[x][y];

        if (peca == null)
            throw new PecaNaoEncontrada();

        return peca;
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
     *
     * @param jogada
     *
     * @return
     *
     * @todo Implementar
     */
    private boolean pecaNoCaminho(Jogada jogada) {
        boolean vert = jogada.movimentoHorizVert();
        boolean horiz = jogada.movimentoHorizontal();
        boolean diag = jogada.movimentoDiagonal();

        boolean horizVert = vert || horiz;

        if (!horizVert && !diag)
            return false;

        // IPeca peca = jogada.getPeca();
        //
        // for (int i = peca.getX(), j; i <= jogada.getDestX(); i++) {
        // for (j = peca.getY(); j <= jogada.getDestY(); j++) {
        // if (pecas[i][j] != null) {
        // return true;
        // }
        // }
        // }

        return false;
    }

    /**
     * @param jogada
     * @throws PecaNaoEncontrada
     */
    private void tomar(Jogada jogada) {
        IPeca outra = peca(jogada);

        if (outra != null && jogada.getPeca().getCor() != outra.getCor())
            onTomada(jogada.getPeca(), outra);
    }
}