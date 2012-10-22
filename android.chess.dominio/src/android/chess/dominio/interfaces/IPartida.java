/**
 *
 */
package android.chess.dominio.interfaces;

import java.io.Serializable;

import android.chess.dominio.excecao.JogadaException;
import android.chess.dominio.pecas.interfaces.IPeca.Cor;

/**
 * @author augusteiner
 *
 */
public interface IPartida extends Serializable {
    /**
     * @return
     */
    ITabuleiro getTabuleiro();
    /**
     * @return
     */
    Cor getTurno();
    /**
     * @param origI
     * @param origJ
     * @param destI
     * @param destJ
     *
     * @throws JogadaException
     */
    void jogada(int origI, int origJ, int destI, int destJ) throws JogadaException;
}
