/**
 *
 */
package android.chess.visao;

import android.content.Context;
import android.widget.Toast;

/**
 * @author augusteiner
 *
 */
public class Mensageiro extends Toast {

    private Context context;
    /**
     * @param context
     */
    public Mensageiro(Context context) {
        super(context);

        this.context = context;
    }

    /**
     * @param message
     *
     * @see Mensageiro#alerta(CharSequence, int)
     */
    public void alerta(CharSequence message) {
        alerta(message, LENGTH_SHORT);
    }

    /**
     * @param message
     * @param length
     *
     * @see Toast#makeText(Context, CharSequence, int)
     * @see Toast#makeText(Context, int, int)
     */
    public void alerta(CharSequence message, int length) {
        Toast.makeText(context, String.format("ATENÇÃO!\n\n%s", message), length)
                .show();
    }
}
