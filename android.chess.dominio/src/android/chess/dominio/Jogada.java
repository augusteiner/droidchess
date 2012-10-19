package android.chess.dominio;

import static java.lang.Math.signum;
import android.chess.dominio.excecao.JogadaException;
import android.chess.dominio.interfaces.IJogada;
import android.chess.dominio.pecas.interfaces.IPeca;

/**
 * Representação de uma jogada realizada com uma peça e tendo uma posição de
 * destino.
 *
 * @author augusteiner
 *
 */
public class Jogada implements IJogada {
    private int destI;

    private int destJ;

    private int origJ;

    private int origI;

    /**
     * @param peca
     * @param destI
     * @param destJ
     * @throws JogadaException
     */
    public Jogada(int origI, int origJ, int destI, int destJ)
        throws JogadaException {

        this.origI = origI;
        this.origJ = origJ;

        this.destI = destI;
        this.destJ = destJ;

        if (!coordenadasValidas(origI, origJ, destI, destJ))
            throw new JogadaException(this);
    }
    /**
     * @param peca
     * @param outra
     * @throws JogadaException
     */
    public Jogada(IPeca peca, IPeca outra) throws JogadaException {
        this(peca.getI(), peca.getJ(), outra.getI(), outra.getJ());
    }
    /**
     * @param coordenadas
     * @return
     */
    private boolean coordenadasValidas(int... coordenadas) {
        boolean ret = true;

        for (int c : coordenadas) {
            ret &= coordenadaValida(c);
        }

        return ret;
    }

    /**
     * @param coordenada
     * @return
     */
    private boolean coordenadaValida(int coordenada) {
        return !(coordenada < 0 || coordenada > 7);
    }

    /**
     * @return Linha de destino desta jogada.
     */
    @Override
    public int getDestI() {
        return destI;
    }

    /**
     * @return Coluna de destino desta jogada.
     */
    @Override
    public int getDestJ() {
        return destJ;
    }

    /**
     * @return
     */
    @Override
    public int getOrigI() {
        return origI;
    }

    /**
     * @return
     */
    @Override
    public int getOrigJ() {
        return origJ;
    }

    /**
     * Retorna se a jogada está sendo realizada no sentido inverso do tabuleiro
     * (de baixo para cima).
     *
     * @return
     */
    public boolean invertida() {
        return getDestI() - getOrigI() > 0 && getDestJ() - getOrigJ() > 0;
    }

    /*
     * (non-Javadoc)
     *
     * @see android.chess.dominio.interfaces.IJogada#sentidoI()
     */
    @Override
    public float sentidoI() {
        return signum(getDestI() - getOrigI());
    }

    /*
     * (non-Javadoc)
     *
     * @see android.chess.dominio.interfaces.IJogada#sentidoJ()
     */
    @Override
    public float sentidoJ() {
        return signum(getDestJ() - getOrigJ());
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("(%d:%d) -> (%d:%d)", getOrigI(), getOrigJ(),
            getDestI(), getDestJ());
    }
}