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
public class Resposta implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6060812451523286920L;
    /**
     *
     */
    private Object mensagem;

    /**
     * @param server
     */
    public Resposta() {

    }

    /**
     * @return
     */
    public Object getMensagem() {
        return mensagem;
    }

    /**
     * Calcula a resposta a uma jogada sendo requisitada.
     *
     * @param jogada
     */
    public void responder(Jogada jogada) {
        mensagem = null;
    }

    /**
     * Calcula a resposta a um cadastro de jogador/usuário sendo requisitado.
     *
     * @param jogador
     */
    public void responder(Jogador jogador) {
        mensagem = jogador;
    }

}
