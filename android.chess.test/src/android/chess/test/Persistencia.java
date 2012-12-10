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

        findAUser();

        findUserWithAttr();
    }

    /**
     *
     */
    private void findAUser() {

        android.chess.persistencia.Persistencia p = android.chess.persistencia.Persistencia
            .instancia();

        Iterator<Usuario> users = p.all(Usuario.class, 15);
        Usuario usr = null;

        while (users.hasNext()) {
            usr = users.next();

            System.out.println(usr.getNome());
        }

        // p.delete(Usuario.class, 1);
        p.deletar(usr);

        p.flush();
    }

    /**
     *
     */
    private void findUserWithAttr() {

        android.chess.persistencia.Persistencia p = android.chess.persistencia.Persistencia
            .instancia();

        Usuario u = null;

        try {
            u = p.find(Usuario.class, Usuario.class.getDeclaredField("login"),
                "augusteiner");
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(u.getNome());
    }
}
