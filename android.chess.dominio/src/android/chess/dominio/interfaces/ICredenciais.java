package android.chess.dominio.interfaces;

import java.io.Serializable;

/**
 * Provê interface para objetos que possuam credenciais de acesso.
 * 
 * @author augusteiner
 */
public interface ICredenciais extends Serializable {
    /**
     * @return Login para acesso.
     */
    public abstract String getLogin();
    /**
     * @return Senha para acesso.
     */
    public abstract String getSenha();
}
