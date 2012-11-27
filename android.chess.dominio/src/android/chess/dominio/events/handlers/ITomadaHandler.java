package android.chess.dominio.events.handlers;

import android.chess.dominio.events.info.interfaces.ITomadaInfo;
import android.chess.dominio.excecao.MovimentoException;

/**
 * Interface para handlers que tratem tomada de peças.
 * 
 * @author augusteiner
 */
public interface ITomadaHandler {
    /**
     * Método a ser chamado pelo sistema de eventos do xadrez no momento em que
     * uma tomada de peça ocorra.
     * 
     * @param info
     *            Informações a serem repassadas a este handler.
     * 
     * @throws MovimentoException
     *             O método deve jogar esta exceção caso o movimento deva ser
     *             cancelado.
     */
    public void onTomada(ITomadaInfo info) throws MovimentoException;
}
