package android.chess.server;

import java.io.Serializable;

import android.chess.dominio.Jogada;
import android.chess.dominio.Jogador;

/**
 * @author augusteiner
 *
 */
public class Request implements Serializable {

    public enum Tipo {
        /**
         * Novo cadastro de um usuário. Implica no objeto message de Request ser
         * instância de Jogador.
         */
        CADASTRO,
        /**
         *
         */
        PARTIDA,
        /**
         *
         */
        JOGADA,
    }

    /**
     *
     */
    private static final long serialVersionUID = -2193091130641718696L;

    private Object message;

    private Tipo tipo;

    /**
     * @param tipo
     */
    public Request(Tipo tipo) {
        this.tipo = tipo;
    }

    /**
     * @return
     */
    public Object getMessage() {
        return message;
    }

    /**
     * @return
     */
    public Tipo getTipo() {
        return tipo;
    }

    /**
     * @throws Exception
     */
    public Response handle() throws Exception {

        Response r = new Response();

        switch (tipo) {
            case CADASTRO :
                r.respond((Jogador) message);
            break;
            case PARTIDA :

            break;
            case JOGADA :
                r.respond((Jogada) message);
            break;
            default :
                throw new Exception();
        }

        return r;
    }

    /**
     * @param message
     */
    public void setMessage(Object message) {
        this.message = message;
    }

    /**
     * @param tipo
     */
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}
