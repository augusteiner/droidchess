package android.chess.controle;

import android.chess.client.Cliente;
import android.chess.client.ClienteFactory;
import android.chess.dominio.Usuario;
import android.chess.dominio.factories.CredenciaisFactory;
import android.chess.dominio.interfaces.ICredenciais;
import android.chess.dominio.interfaces.IUsuario;
import android.chess.server.exceptions.RequisicaoException;
import android.chess.util.events.interfaces.IAsyncCallback;
import android.chess.visao.interfaces.IMensageiro;

/**
 * @author augusteiner
 *
 */
public class UsuarioControle extends Controle<IUsuario> {
    private Cliente cliente;
    /**
         *
         */
    private IMensageiro mensageiro;
    /*
     * (non-Javadoc)
     *
     * @see android.chess.controle.Controle#getControlado()
     */
    /**
     * @throws Exception
     *
     */
    public UsuarioControle(IMensageiro mensageiro) {
        this.mensageiro = mensageiro;

        try {
            recuperarCliente();
        } catch (Exception e) {
            mensageiro.erro(e);
        }
    }
    /**
     * @param login
     * @param senha
     * @return
     * @throws Throwable
     */
    public void autenticar(String login, String senha,
        IAsyncCallback<IUsuario> callback) {
        try {
            autenticar(CredenciaisFactory.novaInstancia(login, senha), callback);
        } catch (Exception e) {
            mensageiro.erro(e);
        }
    }
    /**
     * @return
     *
     * @throws RequisicaoException
     */
    public void cadastro(String login, String senha, String nome, String email,
        IAsyncCallback<IUsuario> callback) {
        ICredenciais cred = CredenciaisFactory.novaInstancia(login, senha);

        try {
            callback.invoke(getCliente().cadastro(
                new Usuario(cred, email, nome)));
        } catch (Exception e) {
            mensageiro.erro(e);
        }
    }
    /**
     * @return
     * @throws Exception
     */
    public Cliente getCliente() throws Exception {
        recuperarCliente();

        return cliente;
    }
    @Override
    public IUsuario getControlado() {
        return cliente.getUsuario();
    }
    /**
     * @param credenciais
     * @return
     * @throws Exception
     */
    private void autenticar(ICredenciais credenciais,
        IAsyncCallback<IUsuario> callback) {
        try {
            getCliente().autenticar(credenciais);

            callback.invoke(getControlado());
        } catch (Exception e) {
            mensageiro.erro(e);
        }
    }
    /**
     *
     */
    private void recuperarCliente() throws Exception {
        if (cliente == null) {
            try {
                cliente = ClienteFactory.getPadrao();
            } catch (Exception e) {
                throw e;
            }
        }
    }
}