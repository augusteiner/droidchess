package android.chess.dominio;

import java.io.Serializable;

import android.chess.dominio.interfaces.ICredenciais;
import android.chess.dominio.interfaces.IJogador;
import android.chess.dominio.interfaces.IUsuario;

/**
 * @author augusteiner
 *
 */
public class Usuario extends Jogador
    implements
        ICredenciais,
        Serializable,
        IUsuario,
        IJogador {
    /**
     *
     */
    private String email;
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
    private static final long serialVersionUID = 1949083620486065585L;
    /**
     *
     */
    public Usuario() {
        super();
    }
    /**
     * @param credenciais
     * @param nome
     * @param email
     */
    public Usuario(ICredenciais credenciais, String nome, String email) {
        initCredenciais(credenciais);

        initUsuario(nome, email);

    }

    /**
     * @param nome
     */
    public Usuario(String login, String senha, String nome, String email) {
        initUsuario(nome, email);

        initCredenciais(login, senha);
    }

    /**
     * @return
     */
    @Override
    public ICredenciais getCredenciais() {
        return this;
    }

    /*
     * (non-Javadoc)
     *
     * @see android.chess.dominio.interfaces.IUsuario#getEmail()
     */
    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getSenha() {
        return senha;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    /**
     * @param credenciais
     */
    public void setCredenciais(ICredenciais credenciais) {
        initCredenciais(credenciais);
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return getNome();
    }
    /**
     * @param credenciais
     */
    private void initCredenciais(ICredenciais credenciais) {
        initCredenciais(credenciais.getLogin(), credenciais.getSenha());
    }
    /**
     * @param login
     * @param senha
     */
    private void initCredenciais(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }
    /**
     * @param nome
     * @param email
     */
    private void initUsuario(String nome, String email) {
        this.setNome(nome);

        this.email = email;
    }
}