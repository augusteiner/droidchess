/**
 *
 */
package android.chess.visao;

import android.app.Activity;
import android.chess.util.events.interfaces.IAsyncCallback;
import android.content.Context;
import android.content.Intent;

/**
 * @author augusteiner
 *
 */
public class ActivityCallback<T> implements IAsyncCallback<T> {
    /**
     *
     */
    private Context context;
    /**
     *
     */
    private Intent intent;
    /**
     * @param context
     */
    public ActivityCallback(Context context, Class<? extends Activity> clazz) {

        this(context, new Intent(context, clazz));
    }
    /**
     * @param context
     * @param intent
     */
    public ActivityCallback(Context context, Intent intent) {
        this.context = context;

        this.intent = intent;
    }
    /**
     * @return
     */
    public Intent getIntent() {
        return intent;
    }
    /*
     * (non-Javadoc)
     *
     * @see
     * android.chess.util.events.interfaces.IAsyncCallback#invoke(java.lang.
     * Object)
     */
    public void invoke(T arg) {

        // intent.putExtra(IUsuario.Key.name, usuario);

        context.startActivity(getIntent());
    }

}
