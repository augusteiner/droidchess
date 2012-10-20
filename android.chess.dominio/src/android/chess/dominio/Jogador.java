package android.chess.dominio;

import java.io.Serializable;

/**
 * @author augusteiner
 *
 */
public class Jogador implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1949083620486065585L;
    /**
     *
     */
    private String nome;

    /**
     * @deprecated
     */
    @Deprecated
    Jogador() {

    }

    /**
     * @param nome
     */
    public Jogador(String nome) {
        this.nome = nome;
    }
    /**
     * @return
     */
    public String getNome() {
        return nome;
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