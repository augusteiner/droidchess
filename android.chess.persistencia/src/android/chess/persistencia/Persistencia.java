package android.chess.persistencia;

import java.lang.reflect.Field;
import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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
     * Classe estática para armazenar instância única de serviços de
     * persistência.
     *
     * @author augusteiner
     */
    private static class Holder {
        /**
         * Instância única de {@link Persistencia}.
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
        beginTransaction();
    }
    /**
     * Retorna iterator para todos os objetos persistidos da classe
     * <code>clazz</code> informada.
     *
     * @param clazz
     *            Classe dos objetos a serem recuperados.
     *
     * @return {@link Iterator} para a lista de objetos encontrados.
     */
    public <T> Iterator<T> all(Class<T> clazz) {

        TypedQuery<T> q = getQueryAll(clazz);

        return q.getResultList().iterator();
    }
    /**
     * Retorna iterator para todos os objetos persistidos da classe
     * <code>clazz</code> informada com um <code>limite</code> na quantidade de
     * objetos.
     *
     * @param clazz
     *            Classe dos objetos a serem recuperados.
     *
     * @param limite
     *            Limite para a quantidade a ser retornada.
     *
     * @return {@link Iterator} para a lista de objetos encontrados.
     */
    public <T> Iterator<T> all(Class<T> clazz, int limite) {
        TypedQuery<T> q = getQueryAll(clazz);

        q.setMaxResults(limite);

        return q.getResultList().iterator();
    }
    /**
     *
     */
    public void beginTransaction() {
        EntityTransaction t = getEM().getTransaction();

        if (!t.isActive())
            getEM().getTransaction().begin();
    }
    /**
     *
     */
    public void commit() {
        getEM().getTransaction().commit();
    }
    /**
     * @param cq
     * @return
     */
    public <T> TypedQuery<T> createQuery(CriteriaQuery<T> cq) {
        return getEM().createQuery(cq);
    }
    /**
     * Deleta um objeto persistido de acordo com sua <code>clazz</code> e
     * <code>id</code> informados.
     *
     * @param clazz
     *            Classe do objeto a ser removido.
     *
     * @param id
     *            Identificador do objeto a ser removido.
     */
    public <T> void deletar(Class<T> clazz, Object id) {
        Object ref = getEM().getReference(clazz, id);

        deletar(ref);
    }
    /**
     * Deleta o <code>objeto</code> informado.
     *
     * @param objeto
     *            Objeto a ser removido do estado persistente.
     */
    public void deletar(Object objeto) {
        getEM().remove(objeto);
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
    public <T> T find(Class<T> uclass, String field, Object value)
        throws NoSuchFieldException {
        try {
            return find(uclass, uclass.getDeclaredField(field), value);
        } catch (SecurityException e) {
            throw e;
        } catch (NoSuchFieldException e) {
            throw e;
        }
    }
    /**
     * @param clazz
     * @param field
     * @param value
     * @param index
     * @param value
     * @return
     */
    public <T> Iterator<T> find(Class<T> clazz, String field, Object value,
        int index, int qt) {
        TypedQuery<T> q = getTypedQuery(clazz, field, value);

        q.setFirstResult(index * qt);
        q.setMaxResults(qt);

        return q.getResultList().iterator();
    }
    /**
     *
     */
    public void flush() {
        getEM().flush();
    }
    /**
     * @return
     */
    public CriteriaBuilder getCriteriaBuilder() {
        return getEM().getCriteriaBuilder();
    }
    /**
     * @param entity
     */
    public void persist(Object entity) {
        getEM().persist(entity);
    }
    /**
     *
     */
    public void rollback() {
        getEM().getTransaction().rollback();
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
     * @param class1
     * @param index
     * @param value
     * @return
     */
    private <T> TypedQuery<T> getTypedQuery(Class<T> clazz, String field,
        Object value) {
        EntityManager em = getEM();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);

        Root<T> trans = cq.from(clazz);

        cq.where(cb.equal(trans.get(field), value));

        return em.createQuery(cq);
    }
    /**
     * Retorna instância única para acesso a serviços de persistência.
     *
     * @return Instância da fachada de persistência.
     */
    public static Persistencia instancia() {
        return Holder.INSTANCIA;
    }
}
