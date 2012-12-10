/**
 *
 */
package android.chess.app8;

import android.chess.PartidaAbstractActivity;

/**
 * @author augusteiner
 *
 */
public class Partida extends PartidaAbstractActivity {
    /*
     * (non-Javadoc)
     *
     * @see android.chess.PartidaAbstractActivity#getContentViewLayout()
     */
    @Override
    public int getContentViewLayout() {
        return R.layout.partida;
    }
    /*
     * (non-Javadoc)
     *
     * @see android.chess.PartidaAbstractActivity#getTabuleiroViewId()
     */
    @Override
    public int getTabuleiroViewId() {
        return R.id.tabuleiro;
    }
}
