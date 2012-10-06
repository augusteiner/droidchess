package android.chess.dominio.iterators;

import java.util.Iterator;

/**
 * @author augusteiner
 * 
 */
public class MatrixIterator<T> implements Iterator<T> {
    protected T[][] matrix;
    protected int i = 0;
    protected int j = -1;
    /**
     * @param matrix
     */
    public MatrixIterator(T[][] matrix) {
        this.matrix = matrix;
    }
    /**
     * @return
     */
    public T current() {
        return matrix[i][j];
    }
    /*
     * (non-Javadoc)
     * 
     * @see java.util.Iterator#hasNext()
     */
    @Override
    public boolean hasNext() {
        return i < matrix.length - 1 || j < matrix[0].length - 1;
    }
    /*
     * (non-Javadoc)
     * 
     * @see java.util.Iterator#next()
     */
    @Override
    public T next() {
        j++;

        if (j > matrix.length - 1) {
            i++;
            j = 0;
        }

        return current();
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