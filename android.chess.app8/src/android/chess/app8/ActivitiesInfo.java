/**
 *
 */
package android.chess.app8;

import android.chess.ActivitiesAbstractInfo;
import android.chess.PartidaAbstractActivity;
import android.chess.PartidaActivity;

/**
 * @author augusteiner
 *
 */
public class ActivitiesInfo extends ActivitiesAbstractInfo {
    /**
     * @return
     */
    @Override
    public Class<? extends PartidaAbstractActivity> getPartidaActivityClass() {
        return PartidaActivity.class;
    }
    /**
     * @return
     */
    public static ActivitiesInfo getInstancia() {
        return new ActivitiesInfo();
    }
}
