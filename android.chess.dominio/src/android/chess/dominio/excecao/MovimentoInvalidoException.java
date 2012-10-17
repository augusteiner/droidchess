package android.chess.dominio.excecao;

import android.chess.dominio.pecas.interfaces.IPeca;

/**
 * Exceção a ser jogada quando um movimento de uma peça não for válido.
 * 
 * @author augusteiner
 * 
 * @since 0.1
 */
public class MovimentoInvalidoException extends JogadaException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * @param peca
     */
    public MovimentoInvalidoException(IPeca peca) {
        super(String.format("Peça \'%s\' não pode ser movida.", peca), null);
    }
}