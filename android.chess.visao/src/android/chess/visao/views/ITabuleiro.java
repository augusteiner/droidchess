package android.chess.visao.views;

import java.io.Serializable;

import android.chess.visao.exceptions.InicializacaoException;
import android.view.ViewGroup;

/**
 * @author augusteiner
 *
 */
public interface ITabuleiro extends Serializable {
    /**
     * @param contentView
     *
     * @throws Exception
     */
    void init(ViewGroup contentView) throws InicializacaoException;
}