/**
 *
 */
package android.chess.dominio;

import android.chess.dominio.interfaces.ICadastro;

/**
 * @author augusteiner
 *
 */
public class Cadastro extends Credenciais implements ICadastro {
    /**
     *
     */
    private String email;
    /**
     *
     */
    private String nome;
    /**
     *
     */
    private static final long serialVersionUID = -4670360113093930116L;
    /**
     * @param login
     * @param senha
     */
    public Cadastro(String login, String senha) {
        super(login, senha);
    }
    /*
     * (non-Javadoc)
     *
     * @see android.chess.dominio.interfaces.ICadastro#getEmail()
     */
    @Override
    public String getEmail() {
        return email;
    }
    /*
     * (non-Javadoc)
     *
     * @see android.chess.dominio.interfaces.ICadastro#getNome()
     */
    @Override
    public String getNome() {
        return nome;
    }
    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
}
