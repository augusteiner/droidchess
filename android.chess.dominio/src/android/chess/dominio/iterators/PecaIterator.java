/**
 *
 */
package android.chess.dominio.iterators;

import java.util.Iterator;

import android.chess.dominio.interfaces.IPeca;

/**
 * @author augusteiner
 */
public class PecaIterator implements Iterator<IPeca> {
    private IPeca[][] pecas;
    private int i = 0;
    private int j = -1;

    /**
     *
     */
    public PecaIterator(IPeca[][] pecas) {

        this.pecas = pecas;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.util.Iterator#hasNext()
     */
    @Override
    public boolean hasNext() {
        return i < pecas.length - 1 || j < pecas[0].length - 1;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.util.Iterator#next()
     */
    @Override
    public IPeca next() {
        j++;

        if (j > 7) {
            i++;

            if (i == 2) {
                i = 6;
            }

            j = 0;
        }

        return pecas[i][j];
    }

    /*
     * (non-Javadoc)
     *
     * @see java.util.Iterator#remove()
     */
    @Override
    public void remove() {
        // TODO Jogar uma exceção!?
    }

}
