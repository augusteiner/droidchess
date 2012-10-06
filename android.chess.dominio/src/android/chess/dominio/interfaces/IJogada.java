package android.chess.dominio.interfaces;

import android.chess.dominio.excecao.JogadaException;
import android.chess.dominio.excecao.JogadaInvalida;
import android.chess.dominio.pecas.Peca;

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
     * @return
     */
    public boolean movimentoHorizontal();
    /**
     * @return
     */
    public boolean movimentoVertical();
    /**
     *
     */
    public void realizar() throws JogadaInvalida;
    /**
     * @return
     */
    public float sentidoI();
    /**
     * @return
     */
    public float sentidoJ();
    /**
     * @param outra
     * @throws JogadaException
     */
    public void tomar(Peca outra) throws JogadaException;
}
