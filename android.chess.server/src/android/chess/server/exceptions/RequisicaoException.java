package android.chess.server.exceptions;

import java.io.Serializable;

import android.chess.dominio.excecao.ChessException;
import android.chess.server.comunicacao.Requisicao;

/**
 * Exceção a ser jogada caso a requisição não possa ser atendida pelo servidor.
 *
 * @author augusteiner
 */
public class RequisicaoException extends ChessException implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8657031592932101781L;

    /**
     * @param requisicao
     */
    public RequisicaoException(Requisicao requisicao) {
        this(requisicao, null);
    }

    /**
     * @param requisicao
     * @param cause
     */
    public RequisicaoException(Requisicao requisicao, Throwable cause) {
        super(String.format("Requisição de tipo '%s' não aceita.",
            requisicao.getTipo()), cause);
    }
}
