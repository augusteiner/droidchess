package android.chess.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import android.chess.dominio.Jogador;
import android.chess.dominio.Partida;
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
        private static final Cliente INSTANCIA = new Cliente();
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
     *
     */
    protected Socket socket;
    /**
     *
     */
    private Cliente() {
        try {
            socket = Servidor.novoSocket();

            initStreams();
        } catch (IOException e) {
            e.printStackTrace();

            System.exit(-1);
        }

    }
    /**
     *
     */
    protected Cliente(boolean arg) {

    }
    /**
     * @return
     * @throws Exception
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public Jogador cadastro() throws IOException, ClassNotFoundException,
        Exception {

        Requisicao requisicao = new Requisicao(Tipo.CADASTRO, new Jogador(
            "José Augusto"));
        Resposta resposta = enviar(requisicao);

        return (Jogador) resposta.getMensagem();
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
     * Solicita ao servidor o início de uma partida.
     *
     * @return A partida iniciada pelo servidor.
     *
     * @throws RequisicaoException
     *             Caso algum erro ocorra durante a requisição.
     */
    public static Partida novaPartida() throws RequisicaoException {
        Resposta r = getInstancia().enviar(new Requisicao(Tipo.PARTIDA, null));

        return (Partida) r.getMensagem();
    }
    /**
     * @return
     */
    private static Cliente getInstancia() {
        return Holder.INSTANCIA;
    }
}
