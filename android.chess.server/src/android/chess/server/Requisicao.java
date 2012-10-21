package android.chess.server;

import java.io.Serializable;

import android.chess.dominio.Jogada;
import android.chess.dominio.Jogador;

/**
 * @author augusteiner
 *
 */
public class Requisicao implements Serializable {

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

    private Object mensagem;

    private Tipo tipo;

    /**
     * @param tipo
     * @param mensagem
     */
    public Requisicao(Tipo tipo, Object mensagem) {
        this.tipo = tipo;
        this.mensagem = mensagem;
    }

    /**
     * @return
     */
    public Object getMensagem() {
        return mensagem;
    }

    /**
     * @return
     */
    public Tipo getTipo() {
        return tipo;
    }

    /**
     * Responde a uma requisição representada por esta instância.
     *
     * @throws Exception
     *             Caso o tipo da requisição seja desconhecido.
     */
    public Resposta responder() throws Exception {

        Resposta r = new Resposta();

        switch (tipo) {
            case CADASTRO :
                r.responder((Jogador) mensagem);
            break;
            case PARTIDA :

            break;
            case JOGADA :
                r.responder((Jogada) mensagem);
            break;
            default :
                throw new Exception();
        }

        return r;
    }

    /**
     * @param tipo
     */
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}
