/**
 *
 */
package android.chess.dominio.pecas;

import static java.lang.Math.abs;
import android.chess.dominio.Jogada;
import android.chess.dominio.excecao.MovimentoInvalido;
import android.chess.dominio.interfaces.IPeca;

/**
 * @author augusteiner
 *
 */
public abstract class Peca implements IPeca {
    /**
     * @param peca
     * @param destX
     * @param destY
     * @return
     */
    private static boolean movimentoDiagonal(IPeca peca, int destX, int destY) {
        int origX = peca.getX();
        int origY = peca.getY();

        return abs(origX - destX) == abs(origY - destY);
    }
    private int x;
    private int y;
    private Cor cor;
    private boolean moveu;
    /**
     * @param tabuleiro
     */
    protected Peca(Cor cor) {
        this.cor = cor;

        moveu = false;
    }
    /**
     * @return
     */
    @Override
    public Cor getCor() {
        // TODO Auto-generated method stub
        return cor;
    }

    /**
     * Retorna se a peça já foi movida.
     *
     * @return
     */
    public final boolean getMoveu() {
        return moveu;
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
     * Move a peça da posição atual para a nova posição definida pelos pontos
     * destX e destY.
     *
     * @param destX
     * @param destY
     * @throws MovimentoInvalido
     */
    @Override
    public void mover(int destX, int destY) throws MovimentoInvalido {
        moveu = true;

        set(destX, destY);
    }
    /**
     * @param destX
     * @param destY
     * @return
     */
    public boolean movimentoDiagonal(int destX, int destY) {
        return movimentoDiagonal(this, destX, destY);
    }

    /**
     * Retorna se o movimento da jogada está sendo realizado nas diagonais.
     *
     * @param jogada
     *            Jogada cujo movimento está sendo testado.
     *
     * @return {@link Boolean} True caso o movimento seja na diagonal, False
     *         caso contrário.
     */
    @Override
    public boolean movimentoDiagonal(Jogada jogada) {
        return jogada.getPeca().getX() == jogada.getDestX();
    }

    /**
     * Retorna se o movimento da jogada é horizontal.
     *
     * @param jogada
     *            Jogada a ser testado o tipo de movimento.
     *
     * @return {@link Boolean} True caso o movimento seja na horizontal, False
     *         caso contrário.
     */
    @Override
    public boolean movimentoHorizontal(Jogada jogada) {
        return jogada.getPeca().getY() == jogada.getDestY();
    }

    /**
     * Retorna se o movimento da jogada é vertical ou horizontal.
     *
     * @param jogada
     *            Jogada a ser testado o tipo de movimento.
     *
     * @return {@link Boolean} True caso o movimento seja horizontal ou vertical
     *         e False caso contrário.
     */
    @Override
    public boolean movimentoHorizVert(Jogada jogada) {
        return movimentoHorizontal(jogada) || movimentoDiagonal(jogada);
    }

    /**
     * Altera as duas coordenadas atuais desta peça.
     *
     * @param destX
     * @param destY
     */
    @Override
    public void set(int destX, int destY) {
        setX(destX);
        setY(destY);
    }

    /**
     * Altera a coordenada x atual desta peça.
     *
     * @param x
     *            Nova coordenada x.
     */
    @Override
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Altera a coordenada y atual desta peça.
     *
     * @param y
     *            Nova coordenada y.
     */
    @Override
    public void setY(int y) {
        this.y = y;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("%s %s (%d:%d)",

        this.getClass().getSimpleName(), this.getCor(),

        this.getX(), this.getY());
    }
}
