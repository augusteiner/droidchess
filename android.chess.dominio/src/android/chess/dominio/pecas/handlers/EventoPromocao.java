package android.chess.dominio.pecas.handlers;

import android.chess.dominio.interfaces.IPromocaoInfo;
import android.chess.dominio.interfaces.IJogada;
import android.chess.dominio.interfaces.IPeca;
import android.chess.dominio.interfaces.IPeca.Tipo;
import android.chess.dominio.pecas.Peao;

/**
 * @author augusteiner
 *
 */
public class EventoPromocao extends EventoMover implements IPromocaoInfo {
    private Tipo tipo;

    /**
     * @param alvo
     */
    public EventoPromocao(Peao alvo, IJogada jogada) {
        super(alvo, jogada.getDestI(), jogada.getDestJ());

        // Valor padrão é a teoricamente "melhor" peça do jogo.
        tipo = Tipo.Rainha;
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
