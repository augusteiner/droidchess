package android.chess.dominio;

import java.io.Serializable;

/**
 * @author augusteiner
 *
 */
public class Jogador implements Serializable {
    /**
     * Identificador deste jogador.
     */
    private int id;
    /**
     *
     */
    private String nome;
    /**
     * @param nome
     */
    public Jogador(String nome) {
        this.nome = nome;
    }
    /**
     * @deprecated
     */
    @Deprecated
    Jogador() {

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
     *
     */
    private static final long serialVersionUID = 1949083620486065585L;
}