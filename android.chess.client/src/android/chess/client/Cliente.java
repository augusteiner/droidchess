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
public final class Cliente {
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private Socket socket;
    private static final Cliente instancia = new Cliente();

    /**
     *
     */
    private Cliente() {
        try {
            socket = Servidor.novoSocket();

            out = new ObjectOutputStream(socket.getOutputStream());

            // out.flush();

            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            // e.printStackTrace();
            // System.err.println(e);
        }
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

            in.close();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return
     * @throws Exception
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public Partida novaPartida() throws RequisicaoException {
        Resposta r = enviar(new Requisicao(Tipo.PARTIDA, null));

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
            out.writeObject(requisicao);
            out.flush();

            response = (Resposta) in.readObject();
        } catch (Exception e) {
            throw new RequisicaoException(requisicao, e);
        }

        return response;
    }
    /**
     * @return
     */
    public static Cliente getInstancia() {
        return instancia;
    }
}
