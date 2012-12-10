package android.chess.dominio;

import android.chess.dominio.interfaces.IJogador;
import android.chess.dominio.interfaces.IUsuario;
import android.chess.dominio.pecas.interfaces.IPeca.Cor;

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
     * @param usuario
     */
    public Jogador(IUsuario usuario) {

        nome = usuario.getNome();
        id = usuario.getId();
        online = usuario.getOnline();

        cor = ((IJogador) usuario).getCor();

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
    /*
     * (non-Javadoc)
     *
     * @see android.chess.dominio.interfaces.IJogador#getId()
     */
    @Override
    public int getId() {
        return id;
    }
    /*
     * (non-Javadoc)
     *
     * @see android.chess.dominio.interfaces.IJogador#getNome()
     */
    @Override
    public String getNome() {
        return nome;
    }
    /*
     * (non-Javadoc)
     *
     * @see android.chess.dominio.interfaces.IJogador#getOnline()
     */
    @Override
    public boolean getOnline() {
        return online;
    }
    /**
     * @param cor
     */
    @Override
    public void setCor(Cor cor) {
        this.cor = cor;
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
    /**
     * @param online
     */
    public void setOnline(boolean online) {
        this.online = online;
    }

}
