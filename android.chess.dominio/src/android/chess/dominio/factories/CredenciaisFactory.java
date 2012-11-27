/**
 * 
 */
package android.chess.dominio.factories;

import android.chess.dominio.interfaces.ICredenciais;
import android.chess.dominio.seguranca.CredenciaisCript;

/**
 * @author augusteiner
 * 
 */
public class CredenciaisFactory {
    /**
     * @param senha
     * @param email
     * @return
     * @throws Exception
     */
    public static ICredenciais novaInstancia(String login, String senha)
        throws Exception {
        return new CredenciaisCript(login, senha);
    }
}
