package android.chess.controle;

import android.chess.client.ClienteFactory;
import android.chess.dominio.Credenciais;
import android.chess.dominio.Usuario;
import android.chess.dominio.interfaces.ICredenciais;
import android.chess.dominio.interfaces.IJogador;
import android.chess.server.exceptions.RequisicaoException;

/**
 * @author augusteiner
 *
 */
public class UsuarioControle extends Controle<IJogador> {
    /**
     *
     */
    public UsuarioControle() {
        //
    }
    /**
     * @return
     */
    public IJogador autenticar(String usuario, String senha)
        throws RequisicaoException {
        autenticar(new Credenciais(usuario, senha));

        return getControlado();
    }
    /**
     * @return
     *
     * @throws RequisicaoException
     */
    public IJogador cadastro(String usuario, String senha, String email,
        String nome) throws RequisicaoException {
        ICredenciais cred = new Credenciais(usuario, senha);

        return ClienteFactory.getPadrao().cadastro(
            new Usuario(cred, email, nome));
    }
    /*
     * (non-Javadoc)
     *
     * @see android.chess.controle.Controle#getControlado()
     */
    @Override
    public IJogador getControlado() {
        return ClienteFactory.getPadrao().getUsuario();
    }
    /**
     * @param credenciais
     * @return
     * @throws RequisicaoException
     */
    private IJogador autenticar(ICredenciais credenciais)
        throws RequisicaoException {
        ClienteFactory.getPadrao().autenticar(credenciais);

        return getControlado();
    }
}
