package android.chess.dominio.pecas;

import static java.lang.Math.abs;
import static java.lang.Math.signum;
import android.chess.dominio.Jogada;
import android.chess.dominio.Peca;
import android.chess.dominio.events.handlers.IAntesPromocaoHandler;
import android.chess.dominio.events.handlers.IDepoisPromocaoHandler;
import android.chess.dominio.events.info.PromocaoInfo;
import android.chess.dominio.events.info.interfaces.IPromocaoInfo;
import android.chess.dominio.excecao.ChessException;
import android.chess.dominio.excecao.JogadaException;
import android.chess.dominio.excecao.MovimentoException;
import android.chess.dominio.excecao.PromocaoException;
import android.chess.dominio.interfaces.IJogada;
import android.chess.dominio.pecas.interfaces.IPeao;
import android.chess.dominio.pecas.interfaces.IPeca;
import android.chess.util.events.Event;
import android.chess.util.events.interfaces.ICallback;
import android.chess.util.events.interfaces.IEvent;
import android.chess.util.events.interfaces.IHandler;

/**
 * Classe de implementação do peão no xadrez.
 *
 * @author augusteiner
 *
 */
public class Peao extends Peca implements IPeao {
    private int prevI;
    private IEvent<IPromocaoInfo> onAntesPromocao;
    private IEvent<IPromocaoInfo> onDepoisPromocao;

    /**
     * @param tabuleiro
     * @param cor
     */
    public Peao(Cor cor) {
        super(cor);

        prevI = getInitialPreviousI();

        onAntesPromocao = new Event<IPromocaoInfo>();
        onDepoisPromocao = new Event<IPromocaoInfo>();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * android.chess.dominio.pecas.interfaces.IPeao#addOnAntesPromocaoHandler
     * (android.chess.dominio.events.handlers.IAntesPromocaoHandler)
     */
    @Override
    public void addOnAntesPromocaoHandler(final IAntesPromocaoHandler handler) {
        onAntesPromocao.addHandler(new IHandler<IPromocaoInfo>() {

            @Override
            public void handle(Object sender, IPromocaoInfo info)
                throws Exception {
                handler.onAntesPromocao(info);
            }
        });
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * android.chess.dominio.interfaces.IPeao#addOnDepoisPromocaoHandler(android
     * .chess.dominio.pecas.handlers.IDepoisPromocaoHandler)
     */
    @Override
    public void addOnDepoisPromocaoHandler(final IDepoisPromocaoHandler handler) {
        onDepoisPromocao.addHandler(new IHandler<IPromocaoInfo>() {

            @Override
            public void handle(Object sender, IPromocaoInfo info)
                throws Exception {
                handler.onDepoisPromocao(info);
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

        int di = abs(jogada.getDestI() - getInitialPreviousI());

        // Checando condição de promoção.
        if (di == 0 || di == 7) {
            IPromocaoInfo info = new PromocaoInfo(this, jogada,
                promocaoCallback());

            onAntesPromocao(info);
        }
    }

    /**
     * @param info
     * @throws ChessException
     */
    protected void onAntesPromocao(IPromocaoInfo info) throws ChessException {
        try {
            onAntesPromocao.raise(info);
        } catch (Exception e) {
            throw new PromocaoException(this, e);
        }
    }

    /**
     * @param info
     * @throws ChessException
     */
    protected void onDepoisPromocao(IPromocaoInfo info) throws ChessException {
        try {
            onDepoisPromocao.raise(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return
     */
    private ICallback<IPromocaoInfo> promocaoCallback() {
        return new ICallback<IPromocaoInfo>() {
            private boolean invoked = false;

            @Override
            public void invoke(IPromocaoInfo info) throws ChessException {

                if (invoked)
                    return;

                info.setAlvo(promover(info.getTipoPromocao()));

                onDepoisPromocao(info);

                invoked = true;
            }
        };
    }
    /**
     * @param tipo
     * @return
     * @throws MovimentoInvalidoException
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
                throw new PromocaoException(this);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see android.chess.dominio.interfaces.IPeca#mover(int, int)
     */
    @Override
    public void validarJogada(int destI, int destJ) throws ChessException {
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
            throw new MovimentoException(this, destI, destJ);
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
            throw new JogadaException(new Jogada(this, outra));
        }
    }
}