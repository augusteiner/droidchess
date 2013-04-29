/**
 *
 */
package android.chess;

import android.chess.app8.R;

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
    public int getContentLayoutId() {
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

    /*
     * (non-Javadoc)
     * 
     * @see android.chess.PartidaAbstractActivity#getMainViewId()
     */
    @Override
    public int getMainViewId() {
        return R.id.mainLayout;
    }
}
