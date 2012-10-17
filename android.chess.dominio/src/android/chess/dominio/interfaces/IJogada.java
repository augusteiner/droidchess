package android.chess.dominio.interfaces;

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
    public int getOrigI();
    /**
     * @return
     */
    public int getOrigJ();
    /**
     * Sentido da jogada (de baixo para cima ou de cima para baixo).
     *
     * @return Um <code>int</code> simbolizando o sentido do movimento.
     */
    public float sentidoI();
    /**
     * @return
     */
    public float sentidoJ();
}