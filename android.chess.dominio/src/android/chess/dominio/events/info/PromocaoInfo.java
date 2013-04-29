package android.chess.dominio.events.info;

import android.chess.dominio.events.info.interfaces.IPromocaoInfo;
import android.chess.dominio.excecao.ChessException;
import android.chess.dominio.excecao.PromocaoException;
import android.chess.dominio.interfaces.IJogada;
import android.chess.dominio.pecas.Peao;
import android.chess.dominio.pecas.interfaces.IPeao;
import android.chess.dominio.pecas.interfaces.IPeca;
import android.chess.dominio.pecas.interfaces.IPeca.Tipo;
import android.chess.util.events.interfaces.ICallback;

/**
 * @author augusteiner
 * 
 */
public class PromocaoInfo extends MovimentoInfo implements IPromocaoInfo {
    /**
     * 
     */
    private IPeao peao;
    /**
     * 
     */
    private Tipo tipo;
    /**
     * 
     */
    private ICallback<IPromocaoInfo> callback;

    /**
     * @param alvo
     * @param jogada
     * @param callback
     */
    public PromocaoInfo(Peao alvo, IJogada jogada,
        ICallback<IPromocaoInfo> callback) {
        super(alvo, jogada.getDestI(), jogada.getDestJ());

        this.peao = alvo;

        // Valor padrão é a teoricamente "melhor" peça do jogo.
        this.tipo = Tipo.Rainha;

        this.callback = callback;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.chess.dominio.events.info.interfaces.IPromocaoInfo#getCallback()
     */
    @Override
    public void callback() throws ChessException {
        try {
            if (this.callback != null) {
                this.callback.invoke(this);
            }
        } catch (Exception e) {
            throw new PromocaoException(this.peao);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.chess.dominio.events.info.interfaces.IPromocaoInfo#getTipoPromocao
     * ()
     */
    @Override
    public Tipo getTipoPromocao() {
        return this.tipo;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.chess.dominio.events.info.interfaces.IPromocaoInfo#setAlvo(android
     * .chess.dominio.pecas.interfaces.IPeca)
     */
    @Override
    public void setAlvo(IPeca alvo) {
        this.alvo = alvo;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.chess.dominio.events.info.interfaces.IPromocaoInfo#setTipoPromocao
     * (android.chess.dominio.pecas.interfaces.IPeca.Tipo)
     */
    @Override
    public void setTipoPromocao(Tipo tipo) {
        this.tipo = tipo;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.chess.dominio.events.info.interfaces.IPromocaoInfo#setTipoPromocao
     * (int)
     */
    @Override
    public void setTipoPromocao(int tipo) {
        this.tipo = IPeca.Tipo.values()[tipo];
    }
}