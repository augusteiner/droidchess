package android.chess.dominio.interfaces;

/**
 * Provê interface para objetos que possuam credenciais de acesso.
 *
 * @author augusteiner
 */
public interface ICredenciais {
    /**
     * @return Login para acesso.
     */
    public abstract String getLogin();
    /**
     * @return Senha para acesso.
     */
    public abstract String getSenha();
}
