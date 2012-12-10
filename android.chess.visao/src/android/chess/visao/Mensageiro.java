/**
 *
 */
package android.chess.visao;

import android.chess.visao.interfaces.IMensageiro;
import android.content.Context;
import android.widget.Toast;

/**
 * @author augusteiner
 *
 */
public class Mensageiro extends Toast implements IMensageiro {

    private String atencao;
    private Context context;
    private String erro;
    /**
     * @param context
     */
    public Mensageiro(Context context) {
        super(context);

        atencao = context.getResources().getString(R.string.atencao);
        erro = context.getResources().getString(R.string.erro);

        this.context = context;
    }
    /*
     * (non-Javadoc)
     *
     * @see
     * android.chess.visao.interfaces.IMensageiro#alertar(java.lang.CharSequence
     * )
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
        makeText(String.format("%s!\n\n%s", atencao, mensagem), duracao);
    }
    /*
     * (non-Javadoc)
     *
     * @see
     * android.chess.visao.interfaces.IMensageiro#erro(java.lang.CharSequence)
     */
    public void erro(CharSequence mensagem) {
        erro(mensagem, LENGTH_LONG);
    }

    /**
     * @param mensagem
     * @param duracao
     */
    public void erro(CharSequence mensagem, int duracao) {
        makeText(String.format("%s!\n\n%s", erro, mensagem), duracao);
    }
    /*
     * (non-Javadoc)
     *
     * @see android.chess.visao.interfaces.IMensageiro#erro(java.lang.Exception)
     */
    public void erro(Exception e) {
        e.printStackTrace();

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