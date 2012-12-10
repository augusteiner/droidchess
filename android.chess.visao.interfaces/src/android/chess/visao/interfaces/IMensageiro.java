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
     * @param duracao
     */
    public void alertar(CharSequence mensagem, int duracao);
    /**
     * @param e
     */
    public void erro(Exception e);
}
