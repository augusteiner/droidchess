package android.chess.dominio;

import android.chess.dominio.interfaces.IJogador;
import android.chess.dominio.interfaces.IUsuario;
import android.chess.dominio.pecas.interfaces.IPeca.Cor;

/**
 * @author augusteiner
 * 
 */
public class Jogador implements IJogador {
    /**
     *
     */
    private Cor cor;
    /**
     *
     */
    private int id;
    /**
     *
     */
    private String nome;
    /**
     *
     */
    private boolean online;
    /**
     *
     */
    private static final long serialVersionUID = -2697601184492517631L;

    /**
     *
     */
    public Jogador() {

    }

    /**
     * @param cor
     */
    public Jogador(Cor cor) {
        this.cor = cor;
    }

    /**
     * @param usuario
     * 
     * @deprecated
     */
    @Deprecated
    public Jogador(IUsuario usuario) {

        this(usuario.getCor());

        this.nome = usuario.getNome();
        this.id = usuario.getId();
        this.online = usuario.getOnline();
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.chess.dominio.interfaces.IJogador#getCor()
     */
    @Override
    public Cor getCor() {
        return this.cor;
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.chess.dominio.interfaces.IJogador#getId()
     */
    @Override
    public int getId() {
        return this.id;
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.chess.dominio.interfaces.IJogador#getNome()
     */
    @Override
    public String getNome() {
        return this.nome;
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.chess.dominio.interfaces.IJogador#getOnline()
     */
    @Override
    public boolean getOnline() {
        return this.online;
    }

    /**
     * @param cor
     */
    @Override
    public void setCor(Cor cor) {
        this.cor = cor;
    }

    /**
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @param online
     */
    public void setOnline(boolean online) {
        this.online = online;
    }

}
