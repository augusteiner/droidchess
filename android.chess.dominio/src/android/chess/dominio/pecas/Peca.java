/**
 *
 */
package android.chess.dominio.pecas;

import android.chess.dominio.interfaces.IPeca;

/**
 * @author augusteiner
 *
 */
public abstract class Peca implements IPeca {
    private int x, y;
    private Cor cor;

    /**
     * @param tabuleiro
     */
    protected Peca(Cor cor) {
        this.cor = cor;
    }

    /*
     * (non-Javadoc)
     *
     * @see android.chess.dominio.interfaces.IPeca#getX()
     */
    @Override
    public int getX() {
        // TODO Auto-generated method stub
        return x;
    }

    /*
     * (non-Javadoc)
     *
     * @see android.chess.dominio.interfaces.IPeca#getY()
     */
    @Override
    public int getY() {
        // TODO Auto-generated method stub
        return y;
    }

    /**
     * Altera a coordenada x atual desta peça.
     *
     * @param x
     *            Nova coordenada x.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Altera a coordenada y atual desta peça.
     *
     * @param y
     *            Nova coordenada y.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return
     */
    @Override
    public Cor getCor() {
        // TODO Auto-generated method stub
        return cor;
    }

    /*
     * (non-Javadoc)
     *
     * @see android.chess.dominio.interfaces.IPeca#getTipo()
     */
    @Override
    public final Tipo getTipo() {
        return Tipo.valueOf(this.getClass().getSimpleName());
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format(
            "%s %s (%d:%d)",

            this.getClass().getName(),
            this.getCor(),

            this.getX(),
            this.getY()
        );
    }
}
