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
    private IPeao peao;
    private Tipo tipo;
    private ICallback<IPromocaoInfo> callback;

    /**
     * @param alvo
     */
    public PromocaoInfo(Peao alvo, IJogada jogada,
        ICallback<IPromocaoInfo> callback) {
        super(alvo, jogada.getDestI(), jogada.getDestJ());

        peao = alvo;
        // Valor padrão é a teoricamente "melhor" peça do jogo.
        tipo = Tipo.Rainha;

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
            callback.invoke(this);
        } catch (Exception e) {
            throw new PromocaoException(peao);
        }
    }

    /**
     * @return
     */
    @Override
    public Tipo getTipoPromocao() {
        return tipo;
    }

    /**
     * @param alvo
     */
    @Override
    public void setAlvo(IPeca alvo) {
        this.alvo = alvo;
    }

    /**
     * @param tipo
     */
    public void setTipoPromocao(Tipo tipo) {
        this.tipo = tipo;
    }
}