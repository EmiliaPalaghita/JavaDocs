package ro.teamnet.zth.api.em;

import java.util.List;

/**
 * Created by Emilia.Palaghita on 13-Jul-17.
 */
public interface EntityManager {
    <T> T findById(Class<T> entityClass, Long id);

    Long getNextIdVal(String tableName, String columnIdName);

    <T> Object insert(T entity);

    <T> List<T> findAll(Class<T> entityClass);

}
