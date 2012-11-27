package android.chess.dominio.excecao;

import android.chess.dominio.pecas.interfaces.IPeao;

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
     * @param peao
     */
    public PromocaoException(IPeao peao) {
        this(peao, null);
    }

    /**
     * @param peao
     * @param cause
     */
    public PromocaoException(IPeao peao, Throwable cause) {

        super(String.format("Falha ao promover %s.", peao), cause);
    }
}
