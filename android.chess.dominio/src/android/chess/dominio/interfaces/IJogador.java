package android.chess.dominio.interfaces;

import android.chess.dominio.pecas.interfaces.IPeca.Cor;

/**
 * @author augusteiner
 * 
 */
public interface IJogador {
    /**
     * @return
     */
    public Cor getCor();
    /**
     * @return
     */
    public String getNome();
}
