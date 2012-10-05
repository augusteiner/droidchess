/**
 *
 */
package android.chess.dominio.iterators;

import android.chess.dominio.interfaces.IPeca;

/**
 * @author augusteiner
 */
public class PecaIterator extends MatrixIterator<IPeca> {
    /**
     *
     */
    public PecaIterator(IPeca[][] pecas) {
        super(pecas);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Iterator#next()
     */
    @Override
    public IPeca next() {
        j++;

        if (j > matrix.length - 1) {
            i++;

            if (i == 2) {
                i = 6;
            }

            j = 0;
        }

        return current();
    }
}