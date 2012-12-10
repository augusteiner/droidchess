/**
 *
 */
package android.chess.test;

import android.chess.visao.interfaces.IMensageiro;

/**
 * @author augusteiner
 *
 */
public class Mensageiro implements IMensageiro {
    /*
     * (non-Javadoc)
     *
     * @see
     * android.chess.visao.interfaces.IMensageiro#alertar(java.lang.CharSequence
     * )
     */
    @Override
    public void alertar(CharSequence mensagem) {
        System.out.println(mensagem);
    }
    /*
     * (non-Javadoc)
     *
     * @see
     * android.chess.visao.interfaces.IMensageiro#alertar(java.lang.CharSequence
     * , int)
     */
    @Override
    public void alertar(CharSequence mensagem, int duracao) {
        System.out.println(mensagem);
    }
    /*
     * (non-Javadoc)
     *
     * @see android.chess.visao.interfaces.IMensageiro#erro(java.lang.Exception)
     */
    @Override
    public void erro(Exception e) {
        System.err.println(e.getMessage());
    }
}