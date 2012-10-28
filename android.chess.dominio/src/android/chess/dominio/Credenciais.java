/**
 *
 */
package android.chess.dominio;

import android.chess.dominio.interfaces.ICredenciais;

/**
 * @author augusteiner
 *
 */
public class Credenciais implements ICredenciais {
    /**
     *
     */
    private String login;
    /**
     *
     */
    private String senha;
    /**
     *
     */
    public Credenciais(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }
    /*
     * (non-Javadoc)
     *
     * @see android.chess.dominio.interfaces.ICredenciais#getLogin()
     */
    @Override
    public String getLogin() {
        return login;
    }
    /*
     * (non-Javadoc)
     *
     * @see android.chess.dominio.interfaces.ICredenciais#getSenha()
     */
    @Override
    public String getSenha() {
        return senha;
    }
}
