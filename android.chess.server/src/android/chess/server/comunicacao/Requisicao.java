package android.chess.server.comunicacao;

import java.io.Serializable;

import android.chess.dominio.Jogada;
import android.chess.dominio.Usuario;
import android.chess.dominio.interfaces.IJogador;

/**
 * @author augusteiner
 *
 */
public class Requisicao implements Serializable {

    public enum Tipo {
        /**
         *
         */
        AUTENTICAR,
        /**
         * Novo cadastro de um usuário. Objeto {@link #mensagem} deve ser
         * instância de {@link IJogador}.
         */
        CADASTRO,
        /**
         * Solicitação de lista de jogadores disponíveis para uma partida.
         * Objeto {@link #mensagem} deve ser <code>null</code>.
         */
        CONVIDAR,
        /**
         * Desconectar do jogo em andamento (ou do servidor).
         */
        DESCONECTAR,
        /**
         * Nova jogada sendo requisitado por um jogador. O objeto
         * {@link #mensagem} desta requisição deve ser um objeto da classe
         * {@link Jogada} com seu jogador e origem/destino da peça a ser jogada.
         */
        JOGADA,
        /**
         * Nova partida a ser iniciada. Objeto {@link #mensagem} deve ser o id
         * do outro jogador a ser convidado para uma partida.
         */
        PARTIDA;
    }

    /**
     * Conteúdo a ser enviado ao servidor por esta requisição.
     */
    private Object mensagem;

    /**
     * {@link Usuario} que está requisitando ao servidor alguma ação. Deve ser
     * <code>null</code> no caso de cadastro de um novo jogador.
     */
    private Usuario remetente;

    /**
     * Tipo da requisição a ser disparada.
     */
    private Tipo tipo;

    /**
     *
     */
    private static final long serialVersionUID = -2193091130641718696L;

    /**
     * @param tipo
     */
    public Requisicao(Tipo tipo) {
        this(tipo, null);
    }

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
    public Usuario getRemetente() {
        return remetente;
    }

    /**
     * @return
     */
    public Tipo getTipo() {
        return tipo;
    }

    /**
     * @param tipo
     */
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}
