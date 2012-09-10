package android.chess.dominio;

import android.chess.dominio.excecao.JogadaInvalida;
import android.chess.dominio.excecao.MovimentoInvalido;
import android.chess.dominio.interfaces.IPeca;

/**
 * @author augusteiner
 *
 */
public class Jogada {
    private int destX, destY;
    private IPeca peca;

    /**
     * @param peca
     * @param destX
     * @param destY
     */
    public Jogada(IPeca peca, int destX, int destY) {
        this.peca = peca;

        this.destX = destX;
        this.destY = destY;
    }

    /**
     * @return
     */
    public int getDestX() {
        return destX;
    }

    /**
     * @return
     */
    public int getDestY() {
        return destY;
    }

    /**
     * @return
     */
    public IPeca getPeca() {
        return peca;
    }

    /**
     * @return
     *
     * @see IPeca#movimentoDiagonal(Jogada)
     */
    public boolean movimentoDiagonal() {
        return peca.movimentoDiagonal(this);
    }

    /**
     * @return
     *
     * @see IPeca#movimentoHorizDiag(Jogada)
     */
    public boolean movimentoHorizDiag() {
        return peca.movimentoHorizDiag(this);
    }

    /**
     * @return
     *
     * @see IPeca#movimentoHorizontal(Jogada)
     */
    public boolean movimentoHorizontal() {
        return peca.movimentoHorizontal(this);
    }

    /**
     * @throws JogadaInvalida
     */
    public void realizar() throws JogadaInvalida {
        try {
            peca.mover(destX, destY);
        } catch (MovimentoInvalido e) {
            throw new JogadaInvalida(this, e);
        }
    }
}
