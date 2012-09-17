package android.chess.dominio.interfaces;

import android.chess.dominio.excecao.JogadaInvalida;

/**
 * @author augusteiner
 *
 */
public interface IJogada {
    /**
     * @return
     */
    public int getDestI();
    /**
     * @return
     */
    public int getDestJ();
    /**
     * @return
     */
    public IPeca getPeca();
    /**
     * @return
     */
    public boolean movimentoDiagonal();
    /**
     *
     */
    public void realizar() throws JogadaInvalida;
    /**
     * @return
     */
    public int sentido();
}
