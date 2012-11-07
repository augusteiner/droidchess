package android.chess.server.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

import android.chess.dominio.Partida;
import android.chess.dominio.Usuario;
import android.chess.dominio.interfaces.ICredenciais;
import android.chess.dominio.interfaces.IJogada;
import android.chess.dominio.pecas.interfaces.IPeca.Cor;
import android.chess.server.comunicacao.Requisicao;
import android.chess.server.comunicacao.Resposta;
import android.chess.server.exceptions.AutenticacaoException;
import android.chess.server.exceptions.RequisicaoException;

/**
 * @author augusteiner
 *
 */
public class Servidor {
    /**
     * @author augusteiner
     *
     */
    private static class Holder {
        /**
         *
         */
        private static final Servidor INSTANCIA = new Servidor();
    }
    /**
     * @author augusteiner
     *
     */
    private class ObjectStreams {
        /**
         *
         */
        private ObjectInputStream in;
        /**
         *
         */
        private ObjectOutputStream out;
        /**
         * @param client
         */
        public ObjectStreams(Socket client) {
            // this.client = client;
        }
    }
    /**
     * Socket servidor associado a esta fachada.
     */
    protected ServerSocket socket;
    /**
     *
     */
    // private Hashtable<Integer, ObjectStreams> clients;
    /**
     *
     * FIXME Colocar configurações em arquivo .xml?
     */
    public static final String address = "10.0.2.2";
    /**
     *
     */
    public static final int port = 9666;
    /**
     * @throws IOException
     */
    public Servidor() {
        try {
            socket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * @param arg
     */
    protected Servidor(boolean arg) {

    }
    /**
     * Chama o método <code>ServerSocket.accept()</code> na instância singleton
     * de socket associada a esta fachada.
     *
     * @return Socket cliente a ser servido.
     *
     * @throws IOException
     *
     * @see {@link ServerSocket#accept()}
     */
    public Socket accept() throws IOException {
        return socket.accept();
    }
    /**
     * Calcula a resposta a um cadastro de jogador/usuário sendo requisitado.
     *
     * @param requisicao
     *
     * @return
     *
     * @throws AutenticacaoException
     *
     * @todo Implementar busca do jogador/usuario e verificar credenciais.
     */
    public Resposta responder(ICredenciais credenciais)
        throws AutenticacaoException {
        if (credenciais.getLogin().equals("android-chess")
            && credenciais.getSenha().equals("123456")) {
            Usuario jogador = new Usuario(credenciais, "Usuário de teste",
                "example@example.org");
            jogador.setCor(Cor.Branca);

            return new Resposta(jogador, jogador);
        } else {
            throw new AutenticacaoException(credenciais);
        }
    }

    /**
     * Calcula a resposta a uma jogada sendo requisitada.
     *
     * @param requisicao
     *
     * @param jogada
     * @return
     */
    public Resposta responder(IJogada jogada) {
        return new Resposta(null, jogada);
    }

    /**
     * Loop principal do servidor esperando por conexão de clientes a serem
     * servidos.
     *
     * @throws Exception
     */
    public void servir() throws Exception {
        while (true) {
            new ServidorThread(this).start();
        }
    }

    /**
     * Serve conteúdo para um cliente conectado via o socket informado.
     *
     * @param client
     *            Socket de comunicação com o cliente.
     *
     * @throws IOException
     *
     * @throws ClassNotFoundException
     */
    public void servir(Socket client) throws IOException,
        ClassNotFoundException, Exception {

        ObjectStreams streams = new ObjectStreams(client);

        streams.in = new ObjectInputStream(client.getInputStream());
        streams.out = new ObjectOutputStream(client.getOutputStream());

        while (true) {
            continuarServindo(streams);
        }
    }
    /**
     * @param streams
     * @throws ClassNotFoundException
     * @throws IOException
     * @throws Exception
     */
    private void continuarServindo(ObjectStreams streams) throws IOException,
        ClassNotFoundException, Exception {

        Requisicao r = (Requisicao) streams.in.readObject();

        Object response = null;

        try {
            response = responder(r);
        } catch (Exception e) {
            if (e instanceof Serializable)
                response = e;
            else
                throw e;
        }

        streams.out.writeObject(response);

        // in.close();

        streams.out.flush();
        // out.close();
    }
    /**
     * Calcula a resposta a ser enviada de acordo com a requisição recebida.
     *
     * @return Resposta a requisição solicitada.
     *
     * @throws RequisicaoException
     *             Caso o tipo da requisição seja desconhecido.
     */
    private Resposta responder(Requisicao r) throws Exception {
        switch (r.getTipo()) {
            case CADASTRO :
                return responder((ICredenciais) r.getMensagem());
            case PARTIDA :
                return responder(r.getRemetente(), (Usuario) r.getMensagem());
            case JOGADA :
                return responder((IJogada) r.getMensagem());
            case DESCONECTAR :
                System.exit(0);
            case AUTENTICAR :
                return responder((ICredenciais) r.getMensagem());
            default :
                throw new RequisicaoException(r);
        }
    }
    /**
     * @param remetente
     * @param mensagem
     * @return
     */
    private Resposta responder(Usuario remetente, Usuario mensagem) {
        return new Resposta(null, new Partida(remetente, mensagem));
    }
    /**
     * Retorna instância única associada a esta fachada.
     *
     * @return
     */
    public static Servidor getInstancia() {
        return Holder.INSTANCIA;
    }
    /**
     * Cria um novo socket de comunicação com o socket servidor associado a esta
     * instância.
     *
     * @return Socket cliente para comunicação com servidor.
     *
     * @throws IOException
     *
     * @see {@link Socket#Socket(String, int)}
     */
    public static Socket novoSocket() throws IOException {
        return new Socket(address, port);
    }

}