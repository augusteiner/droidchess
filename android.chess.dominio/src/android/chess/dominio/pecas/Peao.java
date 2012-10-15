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
import android.chess.dominio.interfaces.IPeao;
import android.chess.dominio.interfaces.IPeca;
import android.chess.dominio.interfaces.IPromocaoInfo;
import android.chess.dominio.interfaces.handlers.IPromocaoHandler;
import android.chess.dominio.pecas.handlers.EventoPromocao;
import android.chess.util.events.Event;
import android.chess.util.events.interfaces.IHandler;

/**
 * Classe de implementação do peão no xadrez.
 *
 * @author augusteiner
 *
 */
public class Peao extends Peca implements IPeao {
    private IPromocaoHandler onPromocaoHandler;
    private int prevI;
    private Event<IPromocaoInfo> onPromocao;

    /**
     * @param tabuleiro
     * @param cor
     */
    public Peao(Cor cor) {
        super(cor);

        prevI = getInitialPreviousI();
    }

    /**
     * @param onPromocaoHandler
     */
    @Override
    public void addOnPromocaoHandler(final IPromocaoHandler onPromocaoHandler) {
        onPromocao.addHandler(new IHandler<IPromocaoInfo>() {

            @Override
            public void onMovimento(Object sender, IPromocaoInfo info)
                throws Exception {
                onPromocaoHandler.onAntesPromocao(info);

                promover(info.getTipoPromocao());

                onPromocaoHandler.onDepoisPromocao(info);
            }
        });
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
    @Override
    public void mover(IJogada jogada, Peca outra) throws ChessException {
        super.mover(jogada, outra);

        int di = jogada.getDestI() - getInitialPreviousI();

        if (di == 0 || di == 7) {
            EventoPromocao evento = new EventoPromocao(this, jogada);

            onAntesPromocao(evento);

            evento.setAlvo(promover(evento.getTipoPromocao()));

            onDepoisPromocao(evento);
        }
    }

    /**
     * @param evento
     * @throws ChessException
     */
    protected void onAntesPromocao(IPromocaoInfo evento) throws ChessException {
        if (onPromocaoHandler != null) {
            onPromocaoHandler.onMovimento(this, evento);
        }
    }

    /**
     * @param evento
     * @throws ChessException
     */
    protected void onDepoisPromocao(IPromocaoInfo evento) throws ChessException {
        if (onPromocaoHandler != null) {
            onPromocaoHandler.onMovimento(this, evento);
        }
    }

    /**
     * @param tipo
     * @return
     * @throws MovimentoInvalido
     */
    protected IPeca promover(Tipo tipo) throws ChessException {
        switch (tipo) {
            case Bispo :
                return new Bispo(this);
            case Rainha :
                return new Rainha(this);
            case Cavalo :
                return new Cavalo(this);
            case Torre :
                return new Torre(this);
            default :
                throw new MovimentoInvalido(this);
        }
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