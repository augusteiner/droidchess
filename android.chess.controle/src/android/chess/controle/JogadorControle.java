package android.chess.controle;

import android.chess.client.ClienteFactory;
import android.chess.dominio.Credenciais;
import android.chess.dominio.interfaces.ICredenciais;
import android.chess.dominio.interfaces.IJogador;
import android.chess.server.exceptions.RequisicaoException;

/**
 * @author augusteiner
 *
 */
public class JogadorControle extends Controle<IJogador> {
    /**
     *
     */
    private IJogador jogador;
    /**
     *
     */
    public JogadorControle() {
        jogador = null;
    }
    /**
     * @return
     */
    public IJogador autenticar(String usuario, String senha)
        throws RequisicaoException {
        return autenticar(new Credenciais(usuario, senha));
    }
    /**
     * @return
     */
    public IJogador cadastro(String usuario, String senha, String email, String nome) throws RequisicaoException {
        ICredenciais cred = new Credenciais(usuario, senha);

        return ClienteFactory.getPadrao().cadastro(new Usuario(cred, email, nome));
    }
    /*
     * (non-Javadoc)
     *
     * @see android.chess.controle.Controle#getControlado()
     */
    @Override
    public IJogador getControlado() {
        return jogador;
    }
    /**
     * @param credenciais
     * @return
     */
    private IJogador autenticar(ICredenciais credenciais) {
        return ClienteFactory.getPadrao().autenticar(credenciais);
    }
}
