/**
 *
 */
package android.chess.dominio.pecas;

import static java.lang.Math.abs;
import android.chess.dominio.Peca;
import android.chess.dominio.events.info.interfaces.ITomadaInfo;
import android.chess.dominio.excecao.MovimentoException;
import android.chess.dominio.excecao.MovimentoInvalidoException;

/**
 * @author augusteiner
 * 
 */
public class Rei extends Peca {

    /**
     *
     */
    public Rei(Cor cor) {
        super(cor);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.chess.dominio.pecas.Peca#onTomada(android.chess.dominio.pecas
     * .EventoTomada)
     */
    @Override
    protected void onTomada(ITomadaInfo evento) throws MovimentoException {

        throw new MovimentoInvalidoException(evento.getOrig());
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.chess.dominio.interfaces.IPeca#mover(int, int)
     */
    @Override
    public void validarJogada(int destI, int destJ) throws MovimentoInvalidoException {
        int di = abs(getI() - destI);
        int dj = abs(getJ() - destJ);

        if (di > 1 || dj > 1)
            throw new MovimentoInvalidoException(this);
    }

}