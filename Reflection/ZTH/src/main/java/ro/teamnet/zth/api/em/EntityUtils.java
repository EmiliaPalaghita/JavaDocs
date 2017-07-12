package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Emilia.Palaghita on 12-Jul-17.
 */
public class EntityUtils {
    private EntityUtils() {
        throw new UnsupportedOperationException();
    }

    public static String getTableName(Class entity) {
        Annotation result = entity.getAnnotation(Table.class);
        if (result != null) {
            return ((Table) result).name();
        } else {
            return entity.getName();
        }
    }

    public static List<ColumnInfo> getColumns(Class entity) {
        Field[] fields = entity.getDeclaredFields();
        List<ColumnInfo> result = new ArrayList<ColumnInfo>();
        for (Field f : fields) {
            Annotation idAnn = f.getAnnotation(Id.class);
            Annotation columnAnn = f.getAnnotation(Column.class);
            if (idAnn != null || columnAnn != null) {
                ColumnInfo colInfo = new ColumnInfo();
                colInfo.setColumnName(f.getName());
                colInfo.setColumnType(f.getType());
                colInfo.setDbColumnName(getTableName(entity));
                if (idAnn != null) {
                    colInfo.setId(true);
                } else {
                    colInfo.setId(false);
                }
                result.add(colInfo);
            }
        }
        return result;
    }

    public static Object castFromSqlType(Object value, Class wantedType) {
        if (value instanceof BigDecimal) {
            value = (BigDecimal) value;
            if (wantedType.getName().equals("java.lang.Integer")) {
                return ((BigDecimal) value).intValue();
            }
            if (wantedType.getName().equals("java.lang.Long")) {

                return ((BigDecimal) value).longValue();
            }

            if (wantedType.getName().equals("java.lang.Float")) {
                return ((BigDecimal) value).floatValue();
            }

            if (wantedType.getName().equals("java.lang.Double")) {
                return ((BigDecimal) value).doubleValue();
            }
        }
        return value;
    }

    public static List<Field> getFieldsByAnnotations(Class clazz, Class annotation) {
        Field[] fields = clazz.getDeclaredFields();
        List<Field> result = new ArrayList<>();
        for (Field f : fields) {
            if (f.getAnnotation(annotation) != null) {
                result.add(f);
            }
        }
        return result;
    }

    public static Object getSqlValue(Object object) throws IllegalAccessException {
        if (object.getClass().getAnnotation(Table.class) != null) {
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field f : fields) {
                if (f.getAnnotation(Id.class) != null) {
                    f.setAccessible(true);
                    return f.get(object.getClass());
                }
            }
        }

        return object;
    }
}
