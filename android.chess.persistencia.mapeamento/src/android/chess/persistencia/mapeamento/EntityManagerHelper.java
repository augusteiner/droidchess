package android.chess.persistencia.mapeamento;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author augusteiner
 *
 */
public class EntityManagerHelper {
    /**
     * @author augusteiner
     *
     */
    private static class Holder {
        /**
         *
         */
        private static final EntityManagerHelper INSTANCIA;
        static {
            INSTANCIA = new EntityManagerHelper();
        }
    }
    private EntityManager em;
    /**
     *
     */
    private EntityManagerHelper() {
        EntityManagerFactory factory = Persistence
            .createEntityManagerFactory(Application.class.getPackage()
                .getName());

        em = factory.createEntityManager();

        em.getTransaction().begin();
    }
    /**
     * @return
     */
    public EntityManager getEntityManger() {
        return em;
    }
    /**
     * @return
     */
    public static EntityManagerHelper instance() {
        return Holder.INSTANCIA;
    }
}
