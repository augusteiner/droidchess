/**
 *
 */
package android.chess.dominio;

import static java.lang.Math.abs;
import android.chess.dominio.events.args.MovimentoArgs;
import android.chess.dominio.events.args.TomadaArgs;
import android.chess.dominio.events.args.interfaces.IMovimentoArgs;
import android.chess.dominio.events.args.interfaces.ITomadaArgs;
import android.chess.dominio.events.handlers.IMovimentoHandler;
import android.chess.dominio.events.handlers.ITomadaHandler;
import android.chess.dominio.excecao.ChessException;
import android.chess.dominio.excecao.MovimentoException;
import android.chess.dominio.interfaces.IJogada;
import android.chess.dominio.pecas.interfaces.IPeca;
import android.chess.util.events.Event;
import android.chess.util.events.interfaces.IHandler;

/**
 * Classe base para todas as peças envolvidas no xadrez.
 * 
 * @author augusteiner
 * 
 */
public abstract class Peca implements IPeca {
    private Cor cor;

    private int i;

    private int j;

    private boolean moveu;

    private Event<IMovimentoArgs> onMovimento;

    private Event<ITomadaArgs> onTomada;
    /**
     *
     */
    private static final long serialVersionUID = 5587581021453027353L;

    /**
     * @param tabuleiro
     */
    protected Peca(Cor cor) {
        this(cor, false);

        onTomada = new Event<ITomadaArgs>();
        onMovimento = new Event<IMovimentoArgs>();
    }

    /**
     * @param cor
     * @param moveu
     */
    protected Peca(Cor cor, boolean moveu) {
        this.cor = cor;

        this.moveu = moveu;
    }

    /**
     * @param outra
     */
    protected Peca(Peca outra) {
        this(outra.getCor(), true);

        // Repassando eventos (e handlers).
        onTomada = outra.onTomada;
        onMovimento = outra.onMovimento;

        outra.onTomada = null;
        outra.onMovimento = null;

        i = outra.getI();
        j = outra.getJ();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.chess.dominio.interfaces.IPeca#addOnMovimentoHandler(android.
     * chess.dominio.interfaces.handlers.IMovimentoHandler)
     */
    @Override
    public void addOnMovimentoHandler(final IMovimentoHandler onMovimentoHandler) {
        onMovimento.addHandler(new IHandler<IMovimentoArgs>() {

            /**
             *
             */
            private static final long serialVersionUID = 1324757291254818261L;

            @Override
            public void handle(Object sender, IMovimentoArgs info)
                throws Exception {
                onMovimentoHandler.onMovimento(sender, info);
            }
        });
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.chess.dominio.interfaces.IPeca#setOnTomadaHandler(android.chess
     * .dominio.interfaces.handlers.ITomadaHandler)
     */
    @Override
    public void addOnTomadaHandler(final ITomadaHandler onTomadaHandler) {
        onTomada.addHandler(new IHandler<ITomadaArgs>() {

            /**
             *
             */
            private static final long serialVersionUID = -1823497395502062889L;

            @Override
            public void handle(Object sender, ITomadaArgs info)
                throws Exception {
                onTomadaHandler.onTomada(info);
            }
        });
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

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.chess.dominio.interfaces.IPeca#setOnMovimentoHandler(android.
     * chess.dominio.interfaces.handlers.IMovimentoHandler)
     */
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
    public final void validarJogada(IJogada jogada) throws ChessException {
        validarJogada(jogada.getDestI(), jogada.getDestJ());
    }

    /**
     * Movimenta esta peça de acordo com o destino do movimento/jogada.
     * 
     * @param jogada
     *            Jogada guardando as informações de destino para esta peça.
     * 
     * @throws ChessException
     *             No caso do movimento não ser válido.
     */
    private void mover(IJogada jogada) throws ChessException {
        mover(jogada.getDestI(), jogada.getDestJ());
    }

    /**
     * Move a peça da posição atual para a nova posição definida pelos pontos
     * destI e destJ.
     * 
     * @param destI
     * 
     * @param destJ
     * 
     * @throws ChessException
     */
    private final void mover(int destI, int destJ) throws ChessException {
        validarJogada(destI, destJ);

        moveu = true;

        realizarMovimento(destI, destJ);
    }

    /**
     * Valida a ação da tomada de outra peça de acordo com esta.
     * 
     * @param outra
     * 
     * @throws MovimentoException
     */
    private void tomar(Peca outra) throws ChessException {
        if (getCor() == outra.getCor()) {
            throw new MovimentoException(this, outra.i, outra.j);
        } else {
            validarTomada(outra);

            TomadaArgs evento = new TomadaArgs(this, outra);

            outra.onTomada(evento);
            onMovimento(evento);

            i = outra.i;
            j = outra.j;
        }
    }
    /**
     * @param destI
     * @param destJ
     */
    protected void acionarMovimento(MovimentoArgs evento) {
        onMovimento(evento);

        setI(evento.getDestI());
        setJ(evento.getDestJ());
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
    protected void onMovimento(MovimentoArgs evento) {
        try {
            onMovimento.raise(evento);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param evento
     * @throws MovimentoException
     */
    protected void onTomada(ITomadaArgs evento) throws MovimentoException {
        try {
            onTomada.raise(evento);
        } catch (MovimentoException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            // throw new MovimentoInvalido(evento.getOrig());
        }
    }

    /**
     * Altera as duas coordenadas atuais desta peça.
     * 
     * @param destI
     * @param destJ
     */
    protected void realizarMovimento(int destI, int destJ) {
        MovimentoArgs evento = new MovimentoArgs(this, destI, destJ);

        acionarMovimento(evento);
    }

    /**
     * Valida a tomada de outra peça sendo realizada por esta peça.
     * 
     * @param outra
     *            Peça a ser tomada.
     * 
     * @throws MovimentoInvalidoException
     *             Caso a tomada não seja válida.
     */
    protected void validarTomada(IPeca outra) throws ChessException {
        validarJogada(outra.getI(), outra.getJ());
    }
}