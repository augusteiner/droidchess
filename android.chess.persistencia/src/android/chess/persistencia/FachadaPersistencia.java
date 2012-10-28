package android.chess.persistencia;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 *
 * @author augusteiner
 *
 */
public class FachadaPersistencia {
    /**
     *
     */
    private static Dictionary<?, ?> db = new Hashtable<Object, Object>();

    /**
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T extends Object> T find(Object id) {
        return (T) db.get(id);
    }

    /**
     * @param entity
     */
    public static void persist(Object entity) {
        find(1);
        // db.put(key, entity);
    }
}
