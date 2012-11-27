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
     * @throws Exception
     * 
     */
    public Credenciais(String login, String senha) {
        initCredenciais(login, senha);
    }

    /**
     * @throws Exception
     * 
     */
    protected Credenciais() {
    }

    /**
     * @param credenciais
     * @throws Exception
     */
    protected Credenciais(ICredenciais credenciais) {
        login = credenciais.getLogin();
        senha = credenciais.getSenha();
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

    /**
     * @param credenciais
     * @throws Exception
     */
    protected void initCredenciais(ICredenciais credenciais) {
        initCredenciais(credenciais.getLogin(), credenciais.getSenha());
    }

    /**
     * @param login
     * @param senha
     * @throws Exception
     */
    protected void initCredenciais(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }
}
