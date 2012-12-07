package android.chess.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import android.chess.dominio.Partida;
import android.chess.dominio.Usuario;
import android.chess.dominio.interfaces.ICredenciais;
import android.chess.dominio.interfaces.IPartida;
import android.chess.dominio.interfaces.IUsuario;
import android.chess.server.comunicacao.Requisicao;
import android.chess.server.comunicacao.Requisicao.Tipo;
import android.chess.server.comunicacao.Resposta;
import android.chess.server.exceptions.ConexaoException;
import android.chess.server.exceptions.RequisicaoException;
import android.chess.server.impl.Servidor;

/**
 * @author augusteiner
 *
 */
public class Cliente {
    /**
     * @author augusteiner
     *
     */
    private static class Holder {
        /**
         *
         */
        private static final Cliente INSTANCIA;
        static {
            INSTANCIA = new Cliente();
        }
    }
    /**
     *
     */
    private ObjectInputStream in;
    /**
     *
     */
    private ObjectOutputStream out;
    /**
     * Usuário autenticado na sessão.
     */
    private Usuario usuario;
    /**
     *
     */
    protected Socket socket;
    /**
     * @throws Exception
     *
     */
    Cliente() {
        try {
            conectar();
        } catch (Exception e) {
            socket = null;

            e.printStackTrace();
        }
    }
    /**
     * @param credenciais
     * @return
     * @throws RequisicaoException
     */
    public void autenticar(ICredenciais credenciais) throws RequisicaoException {

        Resposta r = enviar(new Requisicao(Tipo.AUTENTICAR, credenciais));

        usuario = (Usuario) r.getMensagem();
    }

    /**
     * @return
     * @throws Exception
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public Usuario cadastro(IUsuario usuario) throws RequisicaoException {

        Requisicao requisicao = new Requisicao(Tipo.CADASTRO, usuario);
        Resposta resposta = enviar(requisicao);

        return (Usuario) resposta.getMensagem();
    }
    /**
     * @deprecated
     *
     * @todo Renomear método.
     */
    @Deprecated
    public void dispose() {
        try {
            // out.flush();

            in.close();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retorna o jogador autenticado atualmente.
     *
     * @return
     */
    public Usuario getUsuario() {
        return usuario;
    }
    /**
     * Solicita ao servidor o início de uma partida.
     *
     * @return A partida iniciada pelo servidor.
     *
     * @throws RequisicaoException
     *             Caso algum erro ocorra durante a requisição.
     */
    public IPartida novaPartida() throws RequisicaoException {
        Resposta r = enviar(new Requisicao(Tipo.PARTIDA, null));

        return (Partida) r.getMensagem();
    }
    /**
     * @throws Exception
     */
    private void conectar() throws ConexaoException {
        try {
            socket = Servidor.novoSocket();

            initStreams();
        } catch (IOException e) {
            throw new ConexaoException(e);
        }

    }
    /**
     * @param requisicao
     * @return
     * @throws RequisicaoException
     */
    private Resposta enviar(Requisicao requisicao) throws RequisicaoException {

        Resposta response = null;
        try {
            out.writeObject(requisicao);
            out.flush();

            response = (Resposta) in.readObject();
        } catch (Exception e) {
            throw new RequisicaoException(requisicao, e);
        }

        return response;
    }
    /**
     * @throws IOException
     *
     */
    protected void initStreams() throws IOException {
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }
    /**
     * @return
     * @throws Exception
     */
    static Cliente getInstancia() throws Exception {
        if (Holder.INSTANCIA.socket == null)
            Holder.INSTANCIA.conectar();

        return Holder.INSTANCIA;
    }
}
