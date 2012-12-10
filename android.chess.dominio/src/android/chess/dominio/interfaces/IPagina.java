package android.chess.dominio.interfaces;

import java.io.Serializable;

/**
 * @author augusteiner
 *
 * @deprecated
 */
@Deprecated
public interface IPagina extends Serializable {
    /**
     *
     */
    public static final int limite = 10;
    /**
     * @return
     */
    public int getIndex();
}
