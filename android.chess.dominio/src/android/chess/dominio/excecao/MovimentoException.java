package android.chess.dominio.excecao;

import android.chess.dominio.pecas.interfaces.IPeca;

/**
 * Exceção a ser jogada quando o movimento de uma peça não for válido.
 * 
 * @author augusteiner
 */
public class MovimentoException extends ChessException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * @param orig
     * @param destI
     * @param destJ
     */
    public MovimentoException(IPeca orig, int destI, int destJ) {
        super(String.format("Peça '%s' não pode ser movida para (%d:%d).",
            orig, destI, destJ), null);
    }

    /**
     * @param orig
     * @param dest
     */
    protected MovimentoException(IPeca orig, IPeca dest) {
        this(orig, dest.getI(), dest.getJ());
    }

    /**
     * @param messsage
     * @param cause
     */
    protected MovimentoException(String messsage, Throwable cause) {
        super(messsage, cause);
    }
}