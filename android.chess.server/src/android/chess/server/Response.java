/**
 *
 */
package android.chess.server;

import java.io.Serializable;

import android.chess.dominio.Jogada;
import android.chess.dominio.Jogador;

/**
 * @author augusteiner
 *
 */
public class Response implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6060812451523286920L;
    /**
     *
     */
    private Object message;

    /**
     * @param server
     */
    public Response() {

    }

    /**
     * @return
     */
    public Object getMessage() {
        return message;
    }

    /**
     * @param jogada
     */
    public void respond(Jogada jogada) {
        // TODO Inverter jogada para o outro jogador.
    }

    /**
     * @param jogador
     */
    public void respond(Jogador jogador) {
        message = jogador;
    }

}
