package android.chess.visao.views;

import java.io.Serializable;

import android.chess.dominio.events.handlers.IAntesPromocaoHandler;
import android.chess.visao.exceptions.InicializacaoException;
import android.view.ViewGroup;

/**
 * @author augusteiner
 * 
 */
public interface ITabuleiro extends IAntesPromocaoHandler, Serializable {
    /**
     * @param contentView
     * 
     * @throws Exception
     */
    void init(IAntesPromocaoHandler promotionHandler, ViewGroup contentView)
            throws InicializacaoException;
}