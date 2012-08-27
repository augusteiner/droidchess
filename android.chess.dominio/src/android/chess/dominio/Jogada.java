package android.chess.dominio;

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
    Jogada(IPeca peca, int destX, int destY) {
        this.peca = peca;

        this.destX = destX;
        this.destY = destY;
    }

    /**
     * @return
     */
    public IPeca getPeca() {
        return peca;
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
     * @throws MovimentoInvalido
     */
    public void realizar() throws MovimentoInvalido {
        peca.mover(destX, destY);
    }
}
