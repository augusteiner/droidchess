/**
 *
 */
package android.chess.dominio.pecas;

import static java.lang.Math.abs;
import static java.lang.Math.signum;
import android.chess.dominio.Jogada;
import android.chess.dominio.excecao.ChessException;
import android.chess.dominio.excecao.JogadaInvalida;
import android.chess.dominio.excecao.MovimentoInvalido;
import android.chess.dominio.interfaces.IJogada;
import android.chess.dominio.interfaces.IPeca;
import android.chess.dominio.interfaces.handlers.IPromocaoHandler;
import android.chess.dominio.pecas.handlers.EventoMover;
import android.chess.dominio.pecas.handlers.EventoPromocao;

/**
 * @author augusteiner
 * 
 */
public class Peao extends Peca {
    private IPromocaoHandler onAntesPromocaoHandler;
    private IPromocaoHandler onDepoisPromocaoHandler;
    private int prevI;

    /**
     * @param tabuleiro
     * @param cor
     */
    public Peao(Cor cor) {
        super(cor);

        prevI = getInitialPreviousI();
    }

    /**
     * Retorna o índice anterior inicial do movimento deste peão.
     * 
     * @return
     */
    private int getInitialPreviousI() {
        return 7 * abs(getCor().compareTo(Cor.Branca));
    }

    /**
     * Verifica se a direção do movimento deste peão está conforme as regras de
     * movimento do mesmo.
     * 
     * @return True caso ok, False caso contrário.
     */
    private boolean isDirecaoOk(int destI) {
        int pdi = getI() - prevI;

        if (signum(pdi) > 0) {
            return destI > getI();
        } else {
            return destI < getI();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.chess.dominio.pecas.Peca#mover(android.chess.dominio.interfaces
     * .IJogada, android.chess.dominio.pecas.Peca)
     */
    public void mover(IJogada jogada, Peca outra) throws ChessException {
        super.mover(jogada, outra);

        int di = getI() - getInitialPreviousI();

        if (di == 0 || di == 7) {
            onAntesPromocao(new EventoPromocao(this));
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.chess.dominio.pecas.Peca#onBeforeSet(int, int)
     */
    @Override
    protected void onAntesMovimento(EventoMover evento) {
        prevI = getI();
    }

    /**
     * @param evento
     * @throws ChessException
     */
    protected void onAntesPromocao(EventoPromocao evento) throws ChessException {
        if (onAntesPromocaoHandler != null) {
            onAntesPromocaoHandler.onAntesPromocao(evento);
        }
    }

    /**
     * @param evento
     * @throws ChessException
     */
    protected void onDepoisPromocao(EventoPromocao evento)
            throws ChessException {
        if (onDepoisPromocaoHandler != null) {
            onDepoisPromocaoHandler.onDepoisPromocao(evento);
        }
    }

    /**
     * @param tipo
     * @return
     */
    protected IPeca promover(Tipo tipo) {
        return null;
    }

    /**
     * @param onPromocaoHandler
     */
    public void setOnAntesPromocaoHandler(IPromocaoHandler onPromocaoHandler) {
        this.onAntesPromocaoHandler = onPromocaoHandler;
    }

    /**
     * @param onPromocaoHandler
     */
    public void setOnDepoisPromocaoHandler(IPromocaoHandler onPromocaoHandler) {
        this.onDepoisPromocaoHandler = onPromocaoHandler;
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.chess.dominio.interfaces.IPeca#mover(int, int)
     */
    @Override
    public void validarJogada(int destI, int destJ) throws MovimentoInvalido {
        int di = abs(getI() - destI);
        int dj = abs(getJ() - destJ);
        boolean ok = true;

        if (dj != 0) {
            ok = false;
        } else if (di > 2) {
            ok = false;
        } else if (getMoveu()) {
            if (di > 1) {
                ok = false;
            } else {
                ok = isDirecaoOk(destI);
            }
        } else {
            // Caso especial da 1ª jogada do peão.
            ok = destI != prevI;
        }

        if (!ok)
            throw new MovimentoInvalido(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.chess.dominio.pecas.Peca#validarTomada(android.chess.dominio.
     * interfaces.IPeca)
     */
    @Override
    protected void validarTomada(IPeca outra) throws ChessException {
        int di = abs(outra.getI() - getI());
        int dj = abs(outra.getJ() - getJ());

        if (di == dj && di == 1 && isDirecaoOk(outra.getI())) {
            // Everything ok!
        } else {
            throw new JogadaInvalida(new Jogada(this, outra));
        }
    }
}