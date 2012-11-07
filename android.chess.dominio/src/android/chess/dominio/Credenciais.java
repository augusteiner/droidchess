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
    private static final long serialVersionUID = -2191215794779028045L;
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
    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("Login: %s, senha: %s", login, senha);
    }
}
