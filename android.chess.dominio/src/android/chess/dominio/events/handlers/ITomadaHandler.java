package android.chess.dominio.events.handlers;

import android.chess.dominio.events.info.interfaces.ITomadaInfo;
import android.chess.dominio.excecao.MovimentoException;

/**
 * Handler para execução de ações .
 * 
 * @author augusteiner
 * 
 */
public interface ITomadaHandler {
    /**
     * @param evento
     * 
     * @throws MovimentoException
     */
    public void onTomada(ITomadaInfo evento) throws MovimentoException;
}
