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
            try {
                INSTANCIA = new Cliente();
            } catch (Exception e) {
                throw new ExceptionInInitializerError(e);
            }
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
    public Cliente() throws Exception {
        try {
            socket = Servidor.novoSocket();

            initStreams();
        } catch (IOException e) {
            throw e;
        }
    }
    /**
     *
     */
    protected Cliente(boolean arg) {

    }
    /**
     * @param credenciais
     * @return
     * @throws RequisicaoException
     */
    public void autenticar(ICredenciais credenciais) throws RequisicaoException {

        Resposta r = getInstancia().enviar(
            new Requisicao(Tipo.AUTENTICAR, credenciais));

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

            getInstancia().in.close();

            getInstancia().out.close();
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
        Resposta r = getInstancia().enviar(new Requisicao(Tipo.PARTIDA, null));

        return (Partida) r.getMensagem();
    }
    /**
     * @param requisicao
     * @return
     * @throws RequisicaoException
     */
    private Resposta enviar(Requisicao requisicao) throws RequisicaoException {

        Resposta response = null;
        try {
            getInstancia().out.writeObject(requisicao);
            getInstancia().out.flush();

            response = (Resposta) getInstancia().in.readObject();
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
     */
    static Cliente getInstancia() throws ExceptionInInitializerError {
        return Holder.INSTANCIA;
    }
}
