/**
 *
 */
package android.chess.dominio.pecas;

import static java.lang.Math.abs;
import android.chess.dominio.excecao.ChessException;
import android.chess.dominio.excecao.JogadaException;
import android.chess.dominio.excecao.MovimentoInvalido;
import android.chess.dominio.interfaces.IEventoTomada;
import android.chess.dominio.interfaces.IJogada;
import android.chess.dominio.interfaces.IPeca;
import android.chess.dominio.interfaces.handlers.ITomadaHandler;
import android.chess.dominio.pecas.handlers.EventoMover;
import android.chess.dominio.pecas.handlers.EventoTomada;

/**
 * @author augusteiner
 *
 */
public abstract class Peca implements IPeca {
    private Cor cor;

    private int i;

    private int j;

    private boolean moveu;

    private ITomadaHandler onTomadaHandler;

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
        return cor;
    }

    /*
     * (non-Javadoc)
     *
     * @see android.chess.dominio.interfaces.IPeca#getI()
     */
    @Override
    public int getI() {
        return i;
    }

    /*
     * (non-Javadoc)
     *
     * @see android.chess.dominio.interfaces.IPeca#getJ()
     */
    @Override
    public int getJ() {
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
     * @param jogada
     * @throws ChessException
     */
    private void mover(IJogada jogada) throws ChessException {
        mover(jogada.getDestI(), jogada.getDestJ());
    }
    /*
     * (non-Javadoc)
     *
     * @see
     * android.chess.dominio.interfaces.IPeca#mover(android.chess.dominio.Jogada
     * )
     */
    @Override
    public void mover(IJogada jogada, Peca outra) throws ChessException {
        if (outra != null) {
            tomar(outra);
        } else {
            mover(jogada);
        }
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
    public final void mover(int destI, int destJ) throws ChessException {
        validarJogada(destI, destJ);

        moveu = true;

        set(destI, destJ);
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
     * @param destI
     * @param destJ
     */
    protected void onAntesMovimento(EventoMover evento) {
        //
    }

    /**
     * Pseudo evento a ser chamado após a posição da peça ser alterada.
     *
     * @param destI
     *            Coordenada i de destino.
     *
     * @param destJ
     *            Coordenada j de destino.
     */
    protected void onMovimento(EventoMover evento) {
        // Do nothing!
    }

    /**
     * @param evento
     * @throws JogadaException
     */
    protected void onTomada(IEventoTomada evento) throws JogadaException {
        if (onTomadaHandler != null) {
            onTomadaHandler.onTomada(evento);
        }
    }

    /**
     * Altera as duas coordenadas atuais desta peça.
     *
     * @param destI
     * @param destJ
     */
    protected void set(int destI, int destJ) {
        EventoMover evento = new EventoMover(this, destI, destJ);

        onAntesMovimento(evento);

        setI(destI);
        setJ(destJ);

        onMovimento(evento);
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
     * @see
     * android.chess.dominio.interfaces.IPeca#setOnTomadaHandler(android.chess
     * .dominio.interfaces.handlers.ITomadaHandler)
     */
    @Override
    public void setOnTomadaHandler(ITomadaHandler onTomadaHandler) {
        this.onTomadaHandler = onTomadaHandler;
    }

    /**
     * Valida a ação da tomada de outra peça de acordo com esta.
     *
     * @param outra
     *
     * @throws JogadaException
     */
    private void tomar(Peca outra) throws ChessException {
        if (getCor() == outra.getCor()) {
            throw new MovimentoInvalido(this);
        } else {
            validarTomada(outra);

            i = outra.i;
            j = outra.j;

            outra.onTomada(new EventoTomada(this, outra));
        }
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
    public final void validarJogada(IJogada jogada) throws ChessException {
        validarJogada(jogada.getDestI(), jogada.getDestJ());
    }

    /**
     * Valida a tomada de outra peça sendo realizada por esta peça.
     *
     * @param outra
     *            Peça a ser tomada.
     *
     * @throws MovimentoInvalido
     *             Caso a tomada não seja válida.
     */
    protected void validarTomada(IPeca outra) throws ChessException {
        // throw new MovimentoInvalido(this);
    }
}