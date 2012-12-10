package android.chess.dominio;

import android.chess.dominio.interfaces.IConvite;
import android.chess.dominio.interfaces.IUsuario;

/**
 * @author augusteiner
 */
public class Convite implements IConvite {
    /**
     *
     */
    private IUsuario convidado;
    /**
     *
     */
    private IUsuario convidando;
    /**
     * @param convidando
     * @param convidado
     */
    public Convite(IUsuario convidando, IUsuario convidado) {
        this.convidando = convidando;
        this.convidado = convidado;
    }
    /*
     * (non-Javadoc)
     *
     * @see android.chess.dominio.interfaces.IConvite#getConvidado()
     */
    @Override
    public IUsuario getConvidado() {
        return convidado;
    }
    /*
     * (non-Javadoc)
     *
     * @see android.chess.dominio.interfaces.IConvite#getConvidando()
     */
    @Override
    public IUsuario getConvidando() {
        return convidando;
    }
    /**
     * @param convidado
     */
    public void setConvidado(IUsuario convidado) {
        this.convidado = convidado;
    }
    /**
     * @param convidando
     */
    public void setConvidando(IUsuario convidando) {
        this.convidando = convidando;
    }
}
