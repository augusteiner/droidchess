package android.chess.dominio;

import static java.lang.Math.signum;
import android.chess.dominio.excecao.JogadaException;
import android.chess.dominio.excecao.JogadaInvalida;
import android.chess.dominio.excecao.MovimentoInvalido;
import android.chess.dominio.interfaces.IJogada;
import android.chess.dominio.interfaces.IPeca;
import android.chess.dominio.pecas.Peca;

/**
 * Representação de uma jogada realizada com uma peça e tendo uma posição de
 * destino.
 *
 * @author augusteiner
 *
 */
public class Jogada implements IJogada {
    /**
     * @author augusteiner
     *
     */
    // public class Movimento {
    // /**
    // * Identifica o movimento de uma peça como sendo horizontal.
    // */
    // public static final char HORIZONTAL = 1;
    // /**
    // * Identifica o movimento de uma peça como sendo vertical.
    // */
    // public static final char VERTICAL = 2;
    // /**
    // * Identifica o movimento de uma peça como sendo diagonal.
    // */
    // public static final char DIAGONAL = 4;
    // /**
    // * Identifica o movimento de uma peça como sendo de outra modalidade.
    // */
    // public static final char OUTRO = 0;
    // }
    private int destI, destJ;

    private IPeca peca;

    /**
     * @param peca
     * @param destI
     * @param destJ
     */
    public Jogada(IPeca peca, int destI, int destJ) {
        this.peca = peca;

        this.destI = destI;
        this.destJ = destJ;
    }

    /**
     * @return Linha de destino desta jogada.
     */
    @Override
    public int getDestI() {
        return destI;
    }

    /**
     * @return Coluna de destino desta jogada.
     */
    @Override
    public int getDestJ() {
        return destJ;
    }

    /**
     * @return Peça que está sendo alvo da jogada.
     */
    @Override
    public IPeca getPeca() {
        return peca;
    }

    /**
     * Retorna se a jogada está sendo realizada no sentido inverso do tabuleiro
     * (de baixo para cima).
     *
     * @return
     */
    public boolean invertida() {
        return getDestI() - peca.getI() > 0 && getDestJ() - peca.getJ() > 0;
    }

    /**
     * @return
     *
     * @see IPeca#movimentoDiagonal(Jogada)
     */
    @Override
    public boolean movimentoDiagonal() {
        return Peca.movimentoDiagonal(this);
    }

    /**
     * @return
     *
     * @see IPeca#movimentoHorizDiag(Jogada)
     */
    public boolean movimentoHorizDiag() {
        return Peca.movimentoHorizDiag(this);
    }

    /**
     * @return
     *
     * @see IPeca#movimentoHorizontal(Jogada)
     */
    @Override
    public boolean movimentoHorizontal() {
        return Peca.movimentoHorizontal(this);
    }

    /**
     * @return
     *
     * @see Peca#movimentoHorizVert(Jogada)
     */
    public boolean movimentoHorizVert() {
        return Peca.movimentoHorizVert(this);
    }

    /**
     * @return
     *
     * @see Peca#movimentoVertical(Jogada)
     */
    @Override
    public boolean movimentoVertical() {
        return Peca.movimentoVertical(this);
    }

    /**
     * @throws JogadaInvalida
     */
    @Override
    public void realizar(Peca outra) throws JogadaException {
        try {
            peca.mover(this, outra);
        } catch (MovimentoInvalido e) {
            throw new JogadaInvalida(this, e);
        }
    }

    /**
     * Sentido da jogada (de baixo para cima ou de cima para baixo).
     *
     * @return Um <code>int</code> sinalizando o sentido do movimento.
     */
    @Override
    public float sentidoI() {
        return signum(getDestI() - getPeca().getI());
    }

    /*
     * (non-Javadoc)
     *
     * @see android.chess.dominio.interfaces.IJogada#sentidoJ()
     */
    @Override
    public float sentidoJ() {
        return signum(getDestJ() - getPeca().getJ());
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("%s (%d:%d)", getPeca().getTipo().toString(),
            getDestI(), getDestJ());
    }
}
