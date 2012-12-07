package android.chess.dominio.interfaces;

/**
 * @author augusteiner
 *
 */
public interface IUsuario extends IJogador {
    /**
     * @return
     */
    public ICredenciais getCredenciais();
    /**
     * @return
     */
    public String getEmail();
    /**
     * @return
     */
    @Override
    public String getNome();
}
