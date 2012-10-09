package android.chess.dominio.pecas.handlers;

import android.chess.dominio.interfaces.IEventoPromocao;
import android.chess.dominio.interfaces.IPeca;
import android.chess.dominio.interfaces.IPeca.Tipo;
import android.chess.dominio.pecas.Peao;

/**
 * @author augusteiner
 * 
 */
public class EventoPromocao implements IEventoPromocao {
    private IPeca alvo;
    private Tipo tipo;

    /**
     * @param alvo
     */
    public EventoPromocao(Peao alvo) {
        this.alvo = alvo;

        // Valor padrão é a teoricamente "melhor" peça do jogo.
        tipo = Tipo.Rainha;
    }

    /**
     * @return
     */
    public IPeca getAlvo() {
        return alvo;
    }

    /**
     * @return
     */
    public Tipo getTipoPromocao() {
        return tipo;
    }

    /**
     * @param alvo
     */
    void setAlvo(IPeca alvo) {
        this.alvo = alvo;
    }

    /**
     * @param tipo
     */
    public void setTipoPromocao(Tipo tipo) {
        this.tipo = tipo;
    }
}
