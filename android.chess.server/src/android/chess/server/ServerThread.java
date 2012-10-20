/**
 *
 */
package android.chess.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author augusteiner
 */
public class ServerThread extends Thread {

    private Socket client;

    /**
     * @param client
     * @throws IOException
     */
    public ServerThread(ServerSocket server) throws IOException {
        client = server.accept();
    }

    /**
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void process() throws Exception {
        ObjectInputStream in = new ObjectInputStream(client.getInputStream());

        Request r = (Request) in.readObject();

        ObjectOutputStream out = new ObjectOutputStream(
            client.getOutputStream());

        out.writeObject(r.handle());

        in.close();

        out.flush();
        out.close();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Thread#start()
     */
    @Override
    public void run() {
        try {
            process();
        } catch (Exception e) {
            e.printStackTrace();

            System.exit(-1);
        }
    }
}
