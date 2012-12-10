package android.chess.client;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.net.Socket;
import java.util.ArrayList;

import android.chess.dominio.excecao.ChessException;
import android.chess.dominio.interfaces.ICredenciais;
import android.chess.dominio.interfaces.IJogador;
import android.chess.dominio.interfaces.IPartida;
import android.chess.dominio.interfaces.IUsuario;
import android.chess.server.comunicacao.Requisicao;
import android.chess.server.comunicacao.Requisicao.Tipo;
import android.chess.server.comunicacao.Resposta;
import android.chess.server.exceptions.ConexaoException;
import android.chess.server.exceptions.RequisicaoException;
import android.chess.server.impl.Servidor;
import android.chess.util.events.interfaces.IAsyncCallback;

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
     * Lock para permitir que mais de uma thread utilize-se do envio de
     * mensagens ao servidor.
     */
    private final Object enviarLock = new Object();
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
    private IUsuario usuario;
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
     * Envia as credenciais de um usuário ao servidor para que o mesmo
     * autentique o usuário representado por esta credencial.
     *
     * @param credenciais
     *            Credenciais do usuário previamente cadastrado.
     * @param callback
     *
     * @throws ChessException
     *             Caso algum erro ocorra durante a requisição.
     */
    public void autenticar(ICredenciais credenciais,
        IAsyncCallback<IUsuario> callback) throws ChessException,
        ConexaoException {

        usuario = (IUsuario) requisicao(Tipo.AUTENTICAR, credenciais);

        callback.invoke(usuario);
    }
    /**
     * Realiza um cadastro de um novo usuário.
     *
     * @return O usuário recem cadastrado.
     *
     * @throws ChessException
     *             Caso algum erro ocorra durante a requisição.
     */
    public IUsuario cadastro(IUsuario usuario) throws ChessException {
        return (IUsuario) requisicao(Tipo.CADASTRO, usuario);
    }
    /**
     * Solicita ao servidor o início de uma partida.
     *
     * @param jogador
     *            Jogador a ser convidado.
     *
     * @return A partida iniciada pelo servidor.
     *
     * @throws ChessException
     *             Caso algum erro ocorra durante a requisição.
     */
    public void convidar(IJogador jogador, IAsyncCallback<IPartida> callback)
        throws ChessException {

        IPartida partida = (IPartida) requisicao(Tipo.PARTIDA, jogador);

        if (callback != null)
            callback.invoke(partida);
    }
    /**
     * Requisita uma lista de jogadores disponíveis para uma partida.
     *
     * @param index
     *            Índice da página de jogadores.
     *
     * @throws ChessException
     *             Caso ocorra alguma exceção durante a requisição ao servidor.
     */
    @SuppressWarnings("unchecked")
    public ArrayList<IJogador> convidar(int index) throws ChessException {
        return (ArrayList<IJogador>) requisicao(Tipo.CONVIDAR, index);
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
    public IUsuario getUsuario() {
        return usuario;
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
    private Resposta enviar(Requisicao requisicao) throws ChessException {
        Resposta response = null;

        try {
            synchronized (enviarLock) {
                out.writeObject(requisicao);
                out.flush();

                response = (Resposta) in.readObject();
            }
        } catch (EOFException e) {
            throw new ConexaoException(e);
        } catch (StreamCorruptedException e) {
            throw new ConexaoException(e);
        } catch (Exception e) {
            throw new RequisicaoException(requisicao, e);
        }

        if (response.getMensagem() instanceof ChessException)
            throw (ChessException) response.getMensagem();
        else if (response.getMensagem() instanceof Exception)
            throw new RequisicaoException(requisicao,
                (Exception) response.getMensagem());
        else
            return response;
    }
    /**
     * @param tipo
     * @param mensagem
     * @return
     * @throws ChessException
     */
    private Object requisicao(Tipo tipo, Object mensagem) throws ChessException {
        Requisicao requisicao = new Requisicao(tipo, usuario, mensagem);
        Resposta resposta = enviar(requisicao);

        return resposta.getMensagem();
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
