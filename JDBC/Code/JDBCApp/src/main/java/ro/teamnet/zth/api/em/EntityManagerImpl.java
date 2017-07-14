package ro.teamnet.zth.api.em;

import com.sun.xml.internal.bind.v2.model.core.ID;
import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.database.DBManager;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Emilia.Palaghita on 13-Jul-17.
 */
public class EntityManagerImpl implements EntityManager {
    @Override
    public <T> T findById(Class<T> entityClass, Long id) {
        Connection connection = DBManager.getConnection();

        String tableName = EntityUtils.getTableName(entityClass);

        List<ColumnInfo> columns = EntityUtils.getColumns(entityClass);

        List<Field> fields = EntityUtils.getFieldsByAnnotations(entityClass, Id.class);
        fields.addAll(EntityUtils.getFieldsByAnnotations(entityClass, Column.class));

        String columnName = null;

        Condition condition = new Condition();
        for (ColumnInfo c : columns) {
            if (c.isId()) {
                columnName = c.getDbColumnName();
                break;
            }
        }
        condition.setColumnName(columnName);
        condition.setValue(id);

        QueryBuilder query = new QueryBuilder();
        query.setQueryType(QueryType.SELECT)
                .addQueryColumns(columns)
                .setTableName(tableName)
                .addCondition(condition);

        String createdQuery = query.createQuery();

        T object = null;
        try {
            object = entityClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(createdQuery);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                for (ColumnInfo c : columns) {
                    try {
                        Field f = object.getClass().getDeclaredField(c.getColumnName());
                        f.setAccessible(true);
                        Object value = resultSet.getObject(c.getDbColumnName());
                        value = EntityUtils.castFromSqlType(value, c.getColumnType());
                        f.set(object, value);
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return object;
    }

    @Override
    public Long getNextIdVal(String tableName, String columnIdName) {
        Connection connection = DBManager.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT MAX(" + columnIdName + ") FROM " + tableName);
            resultSet.next();
            Long result = resultSet.getLong(1);
            connection.close();
            return result + 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Long.valueOf(-1);
    }

    @Override
    public <T> Object insert(T entity) {
        Connection connection = DBManager.getConnection();
        String tableName = EntityUtils.getTableName(entity.getClass());
        List<ColumnInfo> columnInfoList = EntityUtils.getColumns(entity.getClass());
        Long idNewEntity = null;
        for (ColumnInfo c : columnInfoList) {
            if (c.isId()) {
                idNewEntity = getNextIdVal(tableName, c.getDbColumnName());
                c.setValue(idNewEntity);
            } else {
                try {
                    Field field = entity.getClass().getDeclaredField(c.getColumnName());
                    field.setAccessible(true);
                    c.setValue(field.get(entity));
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.setTableName(tableName)
                .setQueryType(QueryType.INSERT)
                .addQueryColumns(columnInfoList);

        String query = queryBuilder.createQuery();

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (idNewEntity != null) {
            return findById(entity.getClass(), idNewEntity);
        }
        return null;
    }

    @Override
    public <T> List<T> insertEntities(List<T> entities) {
        Connection connection = DBManager.getConnection();
        List<T> result = new ArrayList<>();
        String tableName = EntityUtils.getTableName(entities.getClass());
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (T entity : entities) {
            List<ColumnInfo> columnInfoList = EntityUtils.getColumns(entity.getClass());
            Long idNewEntity = null;
            for (ColumnInfo c : columnInfoList) {
                if (c.isId()) {
                    idNewEntity = getNextIdVal(tableName, c.getDbColumnName());
                    c.setValue(idNewEntity);
                } else {
                    try {
                        Field field = entity.getClass().getDeclaredField(c.getColumnName());
                        field.setAccessible(true);
                        c.setValue(field.get(entity));
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            QueryBuilder queryBuilder = new QueryBuilder();
            queryBuilder.setTableName(tableName)
                    .setQueryType(QueryType.INSERT)
                    .addQueryColumns(columnInfoList);

            String query = queryBuilder.createQuery();

            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (idNewEntity != null) {
                result.add((T) findById(entity.getClass(), idNewEntity));
            }
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public <T> List<T> findAll(Class<T> entityClass) {
        Connection connection = DBManager.getConnection();
        String tableName = EntityUtils.getTableName(entityClass);
        List<ColumnInfo> columnInfoList = EntityUtils.getColumns(entityClass);
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.setQueryType(QueryType.SELECT)
                .setTableName(tableName)
                .addQueryColumns(columnInfoList);
        String query = queryBuilder.createQuery();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            List<T> resultList = new ArrayList<T>();
            while (resultSet.next()) {
                T instance = entityClass.newInstance();
                for (ColumnInfo c : columnInfoList) {
                    Field field = instance.getClass().getDeclaredField(c.getColumnName());
                    field.setAccessible(true);
                    Object value = resultSet.getObject(c.getDbColumnName());
                    value = EntityUtils.castFromSqlType(value, c.getColumnType());
                    field.set(instance, value);
                }
                resultList.add(instance);
            }
            connection.close();
            return resultList;
        } catch (SQLException | InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> T update(T entity) {
        Connection connection = DBManager.getConnection();
        String tableName = EntityUtils.getTableName(entity.getClass());
        List<ColumnInfo> columnInfoList = EntityUtils.getColumns(entity.getClass());
        Long id = null;
        String columnName = null;
        for (ColumnInfo column : columnInfoList) {
            try {
                Field field = entity.getClass().getDeclaredField(column.getColumnName());
                field.setAccessible(true);
                column.setValue(field.get(entity));
                if (field.get(entity) == null) {
                    System.out.println("Invalid row");
                    return null;
                }
                if (column.isId()) {
                    id = (Long) column.getValue();
                    columnName = column.getDbColumnName();
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        Condition condition = new Condition();
        condition.setValue(id);
        condition.setColumnName(columnName);

        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.setQueryType(QueryType.UPDATE)
                .addQueryColumns(columnInfoList)
                .setTableName(tableName)
                .addCondition(condition);
        String query = queryBuilder.createQuery();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public void delete(Object entity) {
        if (entity == null) {
            System.out.println("Invalid entity");
            return;
        }
        Connection connection = DBManager.getConnection();
        String tableName = EntityUtils.getTableName(entity.getClass());
        List<ColumnInfo> columnInfoList = EntityUtils.getColumns(entity.getClass());
        Long id = null;
        String columnName = null;
        for (ColumnInfo columnInfo : columnInfoList) {
            try {


                Field field = entity.getClass().getDeclaredField(columnInfo.getColumnName());
                field.setAccessible(true);
                columnInfo.setValue(field.get(entity));
                if (field.get(entity) == null) {
                    System.out.println("Invalid row");
                    return;
                }
                if (columnInfo.isId()) {
                    id = (Long) columnInfo.getValue();
                    columnName = columnInfo.getDbColumnName();
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        Condition condition = new Condition();
        condition.setValue(id);
        condition.setColumnName(columnName);
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.setQueryType(QueryType.DELETE)
                .addQueryColumns(columnInfoList)
                .setTableName(tableName)
                .addCondition(condition);

        String query = queryBuilder.createQuery();

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public <T> List<T> findByParams(Class<T> entityClass, Map<String, Object> params) {
        Connection connection = DBManager.getConnection();
        String tableName = EntityUtils.getTableName(entityClass);
        List<ColumnInfo> columnInfoList = EntityUtils.getColumns(entityClass);

        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.setQueryType(QueryType.SELECT)
                .addQueryColumns(columnInfoList)
                .setTableName(tableName);

        for (Map.Entry e : params.entrySet()) {
            Condition condition = new Condition();
            condition.setColumnName((String) e.getKey());
            condition.setValue(e.getValue());
            queryBuilder.addCondition(condition);
        }

        String query = queryBuilder.createQuery();

        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(query);
            ResultSet resultSet = statement.getResultSet();
            List<T> resultList = new ArrayList<T>();
            while (resultSet.next()) {
                T instance = entityClass.newInstance();
                for (ColumnInfo c : columnInfoList) {
                    Field field = instance.getClass().getDeclaredField(c.getColumnName());
                    field.setAccessible(true);
                    Object value = resultSet.getObject(c.getDbColumnName());
                    value = EntityUtils.castFromSqlType(value, c.getColumnType());
                    field.set(instance, value);
                }
                resultList.add(instance);
            }
            connection.close();
            return resultList;
        } catch (SQLException | IllegalAccessException | InstantiationException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }
}
