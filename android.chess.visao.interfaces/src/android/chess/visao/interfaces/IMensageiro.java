package android.chess.visao.interfaces;

/**
 * @author augusteiner
 *
 */
public interface IMensageiro {
    /**
     * @param mensagem
     */
    public void alertar(CharSequence mensagem);
    /**
     * @param mensagem
     */
    public void erro(CharSequence mensagem);
    /**
     * @param e
     */
    public void erro(Exception e);
}
