package android.chess.dominio.interfaces.handlers;

import android.chess.dominio.excecao.JogadaException;
import android.chess.dominio.interfaces.ITomadaInfo;

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
     * @throws JogadaException
     */
    public void onTomada(ITomadaInfo evento) throws JogadaException;
}
