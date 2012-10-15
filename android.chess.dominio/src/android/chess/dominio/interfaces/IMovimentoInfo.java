package android.chess.dominio.interfaces;

/**
 * Interface base para informações de evento a serem repassadas.
 *
 * @author augusteiner
 */
public interface IMovimentoInfo {

    /**
     * Peça alvo do movimento.
     *
     * @return
     */
    IPeca getAlvo();
    /**
     * Linha de destino do movimento.
     *
     * @return Representação entre 0 e 7 da linha de destino do movimento.
     */
    int getDestI();

    /**
     * Coluna de destino do movimento.
     *
     * @return Representação entre 0 e 7 da coluna de destino do movimento.
     */
    int getDestJ();
}