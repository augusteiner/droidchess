package android.chess.dominio;

import java.io.Serializable;

import android.chess.dominio.interfaces.ICredenciais;
import android.chess.dominio.interfaces.IJogador;
import android.chess.dominio.interfaces.IUsuario;
import android.chess.dominio.pecas.interfaces.IPeca.Cor;

/**
 * @author augusteiner
 * 
 */
public class Usuario extends Credenciais
    implements
        Serializable,
        IUsuario,
        IJogador {
    /**
	 *
	 */
    private Cor cor;

    /**
     *
     */
    private String email;

    private int id;

    /**
     *
     */
    private String nome;
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
        super(credenciais);

        initUsuario(nome, email);
    }

    /**
     * @param nome
     */
    public Usuario(String login, String senha, String nome, String email) {
        super(login, senha);

    }

    /*
     * (non-Javadoc)
     * 
     * @see android.chess.dominio.interfaces.IJogador#getCor()
     */
    @Override
    public Cor getCor() {
        return cor;
    }

    /**
     * @return
     */
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

    public int getId() {
        return id;
    }

    /**
     * @return
     */
    @Override
    public String getNome() {
        return nome;
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
     * @param cor
     */
    public void setCor(Cor cor) {
        this.cor = cor;
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

    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return nome;
    }

    /**
     * @param nome
     * @param email
     */
    private void initUsuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }
}