package android.chess.dominio.excecao;

import android.chess.dominio.events.info.interfaces.IPromocaoInfo;

/**
 * @author augusteiner
 *
 */
public class PromocaoException extends MovimentoException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * @param info
     */
    public PromocaoException(IPromocaoInfo info) {
        this(info, null);
    }

    /**
     * @param info
     * @param cause
     */
    public PromocaoException(IPromocaoInfo info, Throwable cause) {
        super(String.format("Falha ao promover %s.", info.getAlvo()), cause);
    }
}
