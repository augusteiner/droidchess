package android.chess.persistencia;

import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import android.chess.dominio.Usuario;
import android.chess.persistencia.mapeamento.EntityManagerHelper;

/**
 * Classe de fachada para os serviços de persistência.
 *
 * @author augusteiner
 */
public class Persistencia {
    /**
     * @author augusteiner
     *
     */
    private static class Holder {
        /**
         *
         */
        private final static Persistencia INSTANCIA;
        static {
            INSTANCIA = new Persistencia();
        }
    }
    /**
     *
     */
    private Persistencia() {

    }
    /**
     * @param clazz
     * @return
     */
    public <T> Iterator<T> all(Class<T> clazz) {

        TypedQuery<T> q = getQueryAll(clazz);

        return q.getResultList().iterator();
    }
    /**
     * @param clazz
     * @param limit
     * @return
     */
    public <T> Iterator<T> all(Class<T> clazz, int limit) {
        TypedQuery<T> q = getQueryAll(clazz);

        q.setMaxResults(limit);

        return q.getResultList().iterator();
    }
    /**
     * @param clazz
     * @param id
     */
    public <T> void delete(Class<T> clazz, Object id) {
        Object ref = getEM().getReference(clazz, id);

        delete(ref);
    }
    /**
     * @param entity
     */
    public void delete(Object entity) {
        getEM().remove(entity);
    }
    /**
     * @param clazz
     * @param id
     * @return
     */
    public <T> T find(Class<T> clazz, Object id) {
        return getEM().getReference(clazz, id);
    }
    /**
     *
     */
    public void flush() {
        EntityManager em = getEM();

        em.flush();

        em.getTransaction().commit();

        em.getTransaction().begin();
    }
    /**
     * @param entity
     */
    public void persist(Object entity) {
        getEM().persist(entity);
    }
    /**
     * @return
     */
    private EntityManager getEM() {
        return EntityManagerHelper.instance().getEntityManger();
    }
    /**
     * @param clazz
     * @return
     */
    private <T> TypedQuery<T> getQueryAll(Class<T> clazz) {
        EntityManager em = getEM();

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> c = builder.createQuery(clazz);

        TypedQuery<T> q = em.createQuery(c);

        return q;
    }
    /**
     * @return
     */
    public static Persistencia instancia() {
        return Holder.INSTANCIA;
    }
}
