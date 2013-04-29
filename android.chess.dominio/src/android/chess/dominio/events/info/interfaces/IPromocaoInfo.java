package android.chess.dominio.events.info.interfaces;

import android.chess.dominio.excecao.ChessException;
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
     * @return
     * @throws Exception
     */
    public void callback() throws ChessException;

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
     * Altera o tipo da peça a qual o peão será promovido.
     * 
     * @param tipo
     */
    public void setTipoPromocao(IPeca.Tipo tipo);

    /**
     * Altera o tipo da peça a qual o peão será promovido.
     * 
     * @param tipo
     *            Número representando o tipo da peça.
     */
    public void setTipoPromocao(int tipo);

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