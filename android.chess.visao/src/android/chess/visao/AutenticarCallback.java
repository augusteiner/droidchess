/**
 *
 */
package android.chess.visao;

import android.app.Activity;
import android.chess.dominio.interfaces.IUsuario;
import android.chess.util.events.interfaces.IAsyncCallback;
import android.content.Intent;

/**
 * @author augusteiner
 *
 */
public class AutenticarCallback implements IAsyncCallback<IUsuario> {
    /**
     *
     */
    private Activity activity;
    /**
     *
     */
    private Class<? extends Activity> clazz;
    /**
     * @param activity
     */
    public AutenticarCallback(Activity activity, Class<? extends Activity> clazz) {
        this.activity = activity;

        this.clazz = clazz;
    }
    /*
     * (non-Javadoc)
     *
     * @see
     * android.chess.util.events.interfaces.IAsyncCallback#invoke(java.lang.
     * Object)
     */
    public void invoke(IUsuario usuario) {
        Intent intent = new Intent(activity, clazz);

        activity.startActivity(intent);
    }

}
