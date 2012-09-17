/**
 *
 */
package android.chess.dominio.pecas;

import static java.lang.Math.abs;
import android.chess.dominio.Jogada;
import android.chess.dominio.excecao.MovimentoInvalido;
import android.chess.dominio.interfaces.IJogada;
import android.chess.dominio.interfaces.IPeca;

/**
 * @author augusteiner
 *
 */
public abstract class Peca implements IPeca {

    /**
     * Retorna se o movimento da peça na jogada é na diagonal. Útil para
     * validação de movimentos de bispos.
     *
     * @param jogada
     * @return
     */
    public static boolean movimentoDiagonal(Jogada jogada) {
        return jogada.getPeca().movimentoDiagonal(jogada.getDestI(),
                jogada.getDestJ());
    }

    /**
     * Retorna se o movimento da peça na jogada é vertical ou horizontal. Útil
     * para validação de movimentos de bispos, torres e rainhas.
     *
     * @param jogada
     * @return
     */
    public static boolean movimentoHorizDiag(Jogada jogada) {
        return movimentoHorizontal(jogada) || movimentoDiagonal(jogada);
    }

    /**
     * Retorna se o movimento da peça na jogada é horizontal. Útil para
     * validação de movimentos de torres.
     *
     * @param jogada
     * @return
     */
    public static boolean movimentoHorizontal(Jogada jogada) {
        return jogada.getPeca().movimentoHorizontal(jogada.getDestI());
    }

    /**
     * @param jogada
     * @return
     */
    public static boolean movimentoHorizVert(Jogada jogada) {
        return jogada.getPeca().movimentoHorizVert(jogada.getDestI(),
                jogada.getDestJ());
    }

    /**
     * @param jogada
     * @return
     */
    public static boolean movimentoVertical(Jogada jogada) {
        return jogada.getPeca().movimentoVertical(jogada.getDestI(),
                jogada.getDestJ());
    }

    private Cor cor;

    private boolean moveu;

    private int i;

    private int j;

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

    /*
     * (non-Javadoc)
     *
     * @see android.chess.dominio.interfaces.IPeca#getI()
     */
    @Override
    public int getI() {
        // TODO Auto-generated method stub
        return i;
    }

    /*
     * (non-Javadoc)
     *
     * @see android.chess.dominio.interfaces.IPeca#getJ()
     */
    @Override
    public int getJ() {
        // TODO Auto-generated method stub
        return j;
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

    /**
     * Move a peça da posição atual para a nova posição definida pelos pontos
     * destI e destJ.
     *
     * @param destI
     * @param destJ
     * @throws MovimentoInvalido
     */
    @Override
    public final void mover(int destI, int destJ) throws MovimentoInvalido {
        moveu = true;

        validar(destI, destJ);

        set(destI, destJ);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * android.chess.dominio.interfaces.IPeca#mover(android.chess.dominio.Jogada
     * )
     */
    @Override
    public void mover(Jogada jogada) throws MovimentoInvalido {
        mover(jogada.getDestI(), jogada.getDestJ());
    }

    /**
     * @param destI
     * @param destJ
     * @return
     */
    @Override
    public boolean movimentoDiagonal(int destI, int destJ) {
        return abs(getI() - destI) == abs(getJ() - destJ);
    }

    /**
     * Retorna se o movimento da jogada é diagonal ou horizontal.
     *
     * @param jogada
     *            Jogada a ser testado o tipo de movimento.
     *
     * @return {@link Boolean} True caso o movimento seja horizontal ou diagonal
     *         e False caso contrário.
     */
    @Override
    public boolean movimentoHorizDiag(int destI, int destJ) {
        return movimentoHorizontal(destI) || movimentoDiagonal(destI, destJ);
    }

    /*
     * (non-Javadoc)
     *
     * @see android.chess.dominio.interfaces.IPeca#movimentoHorizontal(int)
     */
    @Override
    public boolean movimentoHorizontal(int destI) {
        return getI() == destI;
    }

    /*
     * (non-Javadoc)
     *
     * @see android.chess.dominio.interfaces.IPeca#movimentoHorizVert(int, int)
     */
    @Override
    public boolean movimentoHorizVert(int destI, int destJ) {
        return movimentoVertical(destI, destJ) || movimentoHorizontal(destI);
    }

    /*
     * (non-Javadoc)
     *
     * @see android.chess.dominio.interfaces.IPeca#movimentoVertical(int, int)
     */
    @Override
    public boolean movimentoVertical(int destI, int destJ) {
        return getI() != destI && getJ() == destJ;
    }

    /**
     * Altera as duas coordenadas atuais desta peça.
     *
     * @param destI
     * @param destJ
     */
    @Override
    public void set(int destI, int destJ) {
        setI(destI);
        setJ(destJ);
    }

    /**
     * Altera a linha atual desta peça.
     *
     * @param i
     *            Nova linha.
     */
    @Override
    public void setI(int i) {
        this.i = i;
    }

    /**
     * Altera a coordenada y atual desta peça.
     *
     * @param j
     *            Nova coordenada y.
     */
    @Override
    public void setJ(int j) {
        this.j = j;
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

        this.getI(), this.getJ());
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * android.chess.dominio.interfaces.IPeca#validar(android.chess.dominio.
     * Jogada)
     */
    @Override
    public final void validar(IJogada jogada) throws MovimentoInvalido {
        validar(jogada.getDestI(), jogada.getDestJ());
    }
}