package android.chess.persistencia;

import java.lang.reflect.Field;
import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
    protected Persistencia() {

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
     * @param declaredField
     * @param login
     * @return
     */
    public <T> T find(Class<T> clazz, Field field, Object value) {
        EntityManager em = getEM();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> trans = cq.from(clazz);

        cq.where(cb.equal(trans.get(field.getName()), value));

        TypedQuery<T> q = em.createQuery(cq);

        return q.getSingleResult();
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
     * @param uclass
     * @param field
     * @param value
     * @return
     * @throws NoSuchFieldException
     */
    public <T> T find(Class<T> uclass, String field, Object value) throws NoSuchFieldException {
        try {
            return find(uclass, uclass.getDeclaredField(field), value);
        } catch (SecurityException e) {
            throw e;
        } catch (NoSuchFieldException e) {
            throw e;
        }
    }
    /**
     *
     */
    public void flush() {
        EntityManager em = getEM();

        em.flush();

        // FIXME Melhorar código.
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
