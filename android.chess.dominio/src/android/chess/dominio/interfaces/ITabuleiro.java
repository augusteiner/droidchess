/**
 *
 */
package android.chess.dominio.interfaces;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.Iterator;

import android.chess.dominio.excecao.ChessException;
import android.chess.dominio.pecas.interfaces.IPeca;
import android.chess.dominio.pecas.interfaces.IPeca.Cor;

/**
 * @author augusteiner
 * 
 */
public interface ITabuleiro extends Serializable {
    /**
     * @return
     */
    Iterator<IPeca> getMatrizPecas();
    /**
     * @return
     */
    Iterator<IPeca> getPecas();
    /**
     * @return
     */
    Cor getTurno();
    /**
     * @param jogada
     */
    void mover(IJogada jogada) throws ChessException;
    /**
     * @param ps
     */
    void print(PrintStream ps);
}
