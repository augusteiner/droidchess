package android.chess.dominio.interfaces;

import java.io.Serializable;

import android.chess.dominio.pecas.interfaces.IPeca.Cor;

/**
 * @author augusteiner
 *
 */
public interface IJogador extends Serializable {
    /**
     * Limite de entradas a serem exibidas em listas (UI) deste tipo de objeto.
     */
    public static final int limitePagina = 10;
    /**
     * @return
     */
    public Cor getCor();
    /**
     * @return
     */
    public int getId();
    /**
     * @return
     */
    public String getNome();
    /**
     * @return
     */
    public boolean getOnline();
    /**
     * @param cor
     */
    public void setCor(Cor cor);
}
