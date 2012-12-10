package android.chess.dominio.interfaces;

import java.io.Serializable;

/**
 * @author augusteiner
 *
 */
public interface IUsuario extends IJogador, Serializable {
    /**
     * @author augusteiner
     *
     */
    public static class Key {
        /**
         *
         */
        public static final String name;
        static {
            Class<IUsuario> clazz = IUsuario.class;

            name = String.format("%s.%s", clazz.getPackage().getName(),
                clazz.getSimpleName());
        }
    }
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
