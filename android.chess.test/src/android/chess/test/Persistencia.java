package android.chess.test;

import java.util.Iterator;

import android.chess.dominio.Usuario;

public class Persistencia extends Test {

    /*
     * (non-Javadoc)
     *
     * @see android.chess.test.Test#run()
     */
    @Override
    public void run() throws Exception {
        android.chess.persistencia.Persistencia p;
        p = android.chess.persistencia.Persistencia.instancia();

        Iterator<Usuario> users = p.all(Usuario.class, 15);
        Usuario usr = null;

        while (users.hasNext()) {
            usr = users.next();

            System.out.println(usr.getNome());
        }

        // p.delete(Usuario.class, 1);
        p.delete(usr);

        p.flush();
    }
}
