/**
 *
 */
package android.chess.dominio.seguranca;

import android.chess.dominio.Credenciais;
import android.chess.util.security.Digestor;

/**
 * @author augusteiner
 *
 */
public class CredenciaisCript extends Credenciais {

    /**
	 *
	 */
    private static final long serialVersionUID = -2352792866440901892L;

    /**
	 *
	 */
    private static final String UUID = "c185f3ee-2ec3-11e2-adff-f4ce4620587b";

    /**
     * @param credenciais
     * @throws Exception
     */
    public CredenciaisCript(Credenciais credenciais) {
        super(credenciais);
    }

    /**
     * @param login
     * @param senha
     * @throws Exception
     */
    public CredenciaisCript(String login, String senha) {
        super(login, senha);
    }

    /**
	 *
	 */
    protected CredenciaisCript() {

    }

    /**
     * @param login
     * @param senha
     */
    @Override
    protected void initCredenciais(String login, String senha) {
        Digestor md = Digestor.instancia();

        senha = md.digerir(UUID + md.digerir(senha));

        super.initCredenciais(login, senha);
    }
}
