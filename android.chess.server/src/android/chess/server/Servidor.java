package android.chess.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author augusteiner
 *
 */
public class Servidor extends ServerSocket {

    // FIXME Colocar configurações em arquivo .xml?
    /**
     *
     */
    public static final String address = "127.0.0.1";
    /**
     *
     */
    public static final int port = 9666;
    /**
     * @return
     * @throws IOException
     */
    public static Socket novoSocket() throws IOException {
        return new Socket(address, port);
    }

    /**
     * @throws IOException
     */
    public Servidor() throws IOException {
        super(port);
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
    public void servir(Socket client) throws Exception {

        ObjectInputStream in = new ObjectInputStream(client.getInputStream());

        Requisicao r = (Requisicao) in.readObject();

        ObjectOutputStream out = new ObjectOutputStream(
            client.getOutputStream());

        out.writeObject(r.responder());

        in.close();

        out.flush();
        out.close();
    }
}