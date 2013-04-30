package android.chess.dominio.events.args.interfaces;

import android.chess.dominio.pecas.interfaces.IPeca;

/**
 * Interface base para informações de evento a serem repassadas.
 * 
 * @author augusteiner
 */
public interface IMovimentoArgs {

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