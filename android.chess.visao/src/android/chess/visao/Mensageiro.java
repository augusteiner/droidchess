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
     * @param mensagem
     *
     * @see Mensageiro#alertar(CharSequence, int)
     */
    public void alertar(CharSequence mensagem) {
        alertar(mensagem, LENGTH_SHORT);
    }

    /**
     * @param mensagem
     * @param duracao
     *
     * @see Toast#makeText(Context, CharSequence, int)
     * @see Toast#makeText(Context, int, int)
     */
    public void alertar(CharSequence mensagem, int duracao) {
        // FIXME Remover string hardcoded.
        makeText(String.format("ATENÇÃO!\n\n%s", mensagem), duracao);
    }

    /**
     * @param mensagem
     */
    public void erro(CharSequence mensagem) {
        erro(mensagem, LENGTH_SHORT);
    }

    /**
     * @param mensagem
     * @param duracao
     */
    public void erro(CharSequence mensagem, int duracao) {
        makeText(String.format("ERRO!\n\n%s.", mensagem), duracao);
    }

    /**
     * @param e
     */
    public void erro(Exception e) {
        erro(e.getMessage());
    }

    /**
     * @param mensagem
     *
     * @param duracao
     */
    private void makeText(CharSequence mensagem, int duracao) {
        makeText(context, mensagem, duracao).show();
    }
}