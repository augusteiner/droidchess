package android.chess.dominio;

import android.chess.dominio.excecao.JogadaInvalida;
import android.chess.dominio.excecao.MovimentoInvalido;
import android.chess.dominio.interfaces.IPeca;
import android.chess.dominio.pecas.Peca;

/**
 * @author augusteiner
 *
 */
public class Jogada {
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
     * @return
     */
    public int getDestI() {
        return destI;
    }

    /**
     * @return
     */
    public int getDestJ() {
        return destJ;
    }

    /**
     * @return
     */
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
        return peca.getI() - getDestI() > 0 || peca.getJ() - getDestJ() > 0;
    }

    /**
     * @return
     *
     * @see IPeca#movimentoDiagonal(Jogada)
     */
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
    public Boolean movimentoVertical() {
        return Peca.movimentoVertical(this);
    }

    /**
     * @throws JogadaInvalida
     */
    public void realizar() throws JogadaInvalida {
        try {
            peca.mover(destI, destJ);
        } catch (MovimentoInvalido e) {
            throw new JogadaInvalida(this, e);
        }
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
