/**
 *
 */
package android.chess.dominio.iterators;

import java.util.Iterator;

/**
 * @author augusteiner
 * @param <T>
 */
public class MatrixIterator<T> implements Iterator<T> {
    private T[][] matrix;
    private int i = 0;
    private int j = -1;

    /**
     *
     */
    public MatrixIterator(T[][] matrix) {

        this.matrix = matrix;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.util.Iterator#hasNext()
     */
    @Override
    public boolean hasNext() {
        return i < matrix.length || j < matrix[0].length;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.util.Iterator#next()
     */
    @Override
    public T next() {
        j++;

        if (j > 7) {
            i++;
            j = 0;
        }

        return matrix[i][j];
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
