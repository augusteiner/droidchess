/**
 *
 */
package android.chess.app8;

import android.chess.ActivitiesAbstractInfo;
import android.chess.LoginAbstractActivity;
import android.os.Bundle;

/**
 * @author augusteiner
 */
public class LoginActivity extends LoginAbstractActivity {
    /*
     * (non-Javadoc)
     *
     * @see android.chess.LoginAbstractActivity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesAbstractInfo.setInstancia(ActivitiesInfo.getInstancia());

        super.onCreate(savedInstanceState);
    }
}
