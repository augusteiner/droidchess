package android.chess.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import android.chess.dominio.Jogador;
import android.chess.server.Requisicao;
import android.chess.server.Requisicao.Tipo;
import android.chess.server.Resposta;
import android.chess.server.Servidor;

/**
 * @author augusteiner
 *
 */
public class Cliente extends Socket {
    /**
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void request() throws Exception {
        Socket server = Servidor.novoSocket();

        ObjectOutputStream out = new ObjectOutputStream(
            server.getOutputStream());

        Requisicao request = new Requisicao(Tipo.CADASTRO, new Jogador("José Augusto"));

        out.writeObject(request);
        out.flush();

        ObjectInputStream in = new ObjectInputStream(server.getInputStream());
        Resposta response = (Resposta) in.readObject();

        System.out.println(response.getMensagem());
    }
}
