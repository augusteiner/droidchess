package android.chess.dominio.events.info.interfaces;

import android.chess.dominio.pecas.interfaces.IPeca;
import android.chess.dominio.pecas.interfaces.IPeca.Tipo;

/**
 * Interface base para objetos que armazenarão informações sobre o evento da
 * promoção de um peão.
 *
 * @author augusteiner
 *
 */
public interface IPromocaoInfo extends IMovimentoInfo {
    /**
     * Peão sendo promovido.
     *
     * @return
     */
    @Override
    public IPeca getAlvo();
    /**
     * Tipo da peça ao qual o peão será promovido.
     *
     * @return
     */
    public Tipo getTipoPromocao();
    /**
     * Altera a peça alvo do evento de promoção. Deve ser utilizado para
     * atualizar o evento com a peça à qual o peão foi promovido.
     *
     * @param alvo
     *            Peça após promoção do peão sendo: Rainha, Cavalo, Torre ou
     *            Bispo.
     */
    public void setAlvo(IPeca alvo);
}