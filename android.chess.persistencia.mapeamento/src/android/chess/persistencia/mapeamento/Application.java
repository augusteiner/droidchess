package android.chess.persistencia.mapeamento;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import android.chess.dominio.Partida;
import android.chess.dominio.Usuario;
import android.chess.dominio.factories.CredenciaisFactory;

public class Application {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        EntityManagerFactory emFct = Persistence
            .createEntityManagerFactory(Application.class.getPackage()
                .getName());

        EntityManager em = emFct.createEntityManager();

        EntityTransaction t = em.getTransaction();

        t.begin();

        try {
            Usuario u1 = new Usuario();
            u1.setCredenciais(CredenciaisFactory.novaInstancia("augusteiner",
                "123456"));
            u1.setNome("José Augusto");
            u1.setEmail("augusteiner@hotmail.com");

            Usuario u2 = new Usuario();
            u2.setCredenciais(CredenciaisFactory.novaInstancia("augusteiner2",
                "1234567"));
            u2.setNome("José Augusto 2");
            u2.setEmail("augusteiner@hotmail.com");

            Partida p1 = new Partida(u1, u2);

            p1.setVencedor(u2);

            System.out.println(p1.getVencedor().getNome());

            em.persist(p1);

            t.commit();
        } catch (Exception e) {
            t.rollback();

            throw e;
        }
    }
}
