package android.chess.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import android.chess.dominio.Jogador;
import android.chess.server.Request;
import android.chess.server.Request.Tipo;
import android.chess.server.Response;
import android.chess.server.Server;

/**
 * @author augusteiner
 *
 */
public class Client extends Socket {
    /**
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void request() throws Exception {
        Socket server = Server.newSocket();

        ObjectOutputStream out = new ObjectOutputStream(
            server.getOutputStream());

        Request request = new Request(Tipo.CADASTRO);
        request.setMessage(new Jogador("José Augusto"));

        out.writeObject(request);
        out.flush();

        ObjectInputStream in = new ObjectInputStream(server.getInputStream());
        Response response = (Response) in.readObject();

        System.out.println(response.getMessage());
    }
}
