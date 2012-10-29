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
public class Usuario implements Serializable, IUsuario, IJogador {
    private Cor cor;
    /**
     *
     */
    private ICredenciais credenciais;
    /**
     *
     */
    private String email;
    /**
     * Identificador deste jogador.
     */
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
        //
    }
    /**
     * @param nome
     */
    public Usuario(ICredenciais credenciais, String nome, String email) {
        this.credenciais = credenciais;

        this.nome = nome;
        this.email = email;
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
    public ICredenciais getCredenciais() {
        return credenciais;
    }
    @Override
    public String getEmail() {
        return email;
    }
    /**
     * @return O identificador desta instância.
     */
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
    public void setCredenciais(ICredenciais credenciais) {
        this.credenciais = credenciais;
    }
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
}