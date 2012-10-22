package android.chess.server.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

import android.chess.dominio.Jogada;
import android.chess.dominio.Jogador;
import android.chess.dominio.Partida;
import android.chess.server.comunicacao.Requisicao;
import android.chess.server.comunicacao.Resposta;
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
     *
     */
    // private Hashtable<Integer, ObjectStreams> clients;
    /**
     *
     * FIXME Colocar configurações em arquivo .xml?
     */
    public static final String address = "127.0.0.1";
    /**
     *
     */
    public static final int port = 9666;
    /**
     *
     */
    private static final Servidor instancia = new Servidor();
    /**
     * Socket servidor associado a esta fachada.
     */
    private static ServerSocket socket;
    /**
     * @throws IOException
     */
    private Servidor() {
        try {
            socket = new ServerSocket(port);
        } catch (IOException e) {
            // e.printStackTrace();
        }
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
     * Calcula a resposta a uma jogada sendo requisitada.
     *
     * @param requisicao
     *
     * @param jogada
     * @return
     */
    public Resposta responder(Jogada jogada) {
        return new Resposta(null, jogada);
    }

    /**
     * Calcula a resposta a um cadastro de jogador/usuário sendo requisitado.
     *
     * @param requisicao
     *
     * @param jogador
     * @return
     */
    public Resposta responder(Jogador jogador) {
        return new Resposta(jogador, jogador);
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
    private Resposta responder(Jogador remetente, Jogador mensagem) {
        return new Resposta(null, new Partida(remetente, mensagem));
    }
    /**
     * Calcula a resposta a ser enviada de acordo com a requisição recebida.
     *
     * @return Resposta a requisição solicitada.
     *
     * @throws RequisicaoException
     *             Caso o tipo da requisição seja desconhecido.
     */
    private Resposta responder(Requisicao r) throws RequisicaoException {
        switch (r.getTipo()) {
            case CADASTRO :
                return responder((Jogador) r.getMensagem());
            case PARTIDA :
                return responder(r.getRemetente(), (Jogador) r.getMensagem());
            case JOGADA :
                return responder((Jogada) r.getMensagem());
            case DESCONECTAR :
                System.exit(0);
            default :
                throw new RequisicaoException(r);
        }
    }
    /**
     * Retorna instância única associada a esta fachada.
     */
    public static final Servidor getInstancia() {
        return instancia;
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