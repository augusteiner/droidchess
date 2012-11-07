/**
 *
 */
package android.chess.server.exceptions;

import android.chess.dominio.interfaces.ICredenciais;

/**
 * @author augusteiner
 *
 */
public class AutenticacaoException extends RequisicaoException {

    /**
     *
     */
    private static final long serialVersionUID = 3133445056266729850L;

    /**
     * @param requisicao
     */
    public AutenticacaoException(ICredenciais credenciais) {
        this(credenciais, null);
    }

    /**
     * @param requisicao
     * @param cause
     */
    public AutenticacaoException(ICredenciais credenciais, Throwable cause) {
        super(String.format("Credenciais '%s' inválidas.", credenciais), cause);
    }
}
