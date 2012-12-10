/**
 *
 */
package android.chess.app8;

import android.chess.PartidaAbstractActivity;

/**
 * @author augusteiner
 *
 */
public class PartidaActivity extends PartidaAbstractActivity {
    /*
     * (non-Javadoc)
     *
     * @see android.chess.PartidaAbstractActivity#getContentViewLayout()
     */
    @Override
    public int getContentViewLayout() {
        return R.layout.activity_partida;
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
