package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Job;
import ro.teamnet.zth.appl.domain.Location;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Emilia.Palaghita on 12-Jul-17.
 */
public class EntityUtilsTest {

    @Test
    public void testGetTableNameMethod() {
        String tableName = EntityUtils.getTableName(Department.class);
        assertEquals("Table name should be departments!", "departments", tableName);
        tableName = EntityUtils.getTableName(Job.class);
        assertEquals("Table name should be jobs!", "jobs", tableName);
    }

    @Test
    public void testGetColumnsMethod() {
        List<ColumnInfo> columnInfoList = EntityUtils.getColumns(Location.class);
        assertEquals(columnInfoList.size(), 5);
        columnInfoList = EntityUtils.getColumns(Department.class);
        assertEquals(columnInfoList.size(), 3);

    }

    @Test
    public void testCastFromSqlTypeMethod() {
        Object o = EntityUtils.castFromSqlType(new BigDecimal(1), Integer.class);
        assertEquals(Integer.class.getName(), o.getClass().getName());

        o = EntityUtils.castFromSqlType(new String(), Integer.class);
        assertEquals(String.class.getName(), o.getClass().getName());
    }

    @Test
    public void testGetFieldsByAnnotationsMethod() {
        List<Field> fields = EntityUtils.getFieldsByAnnotations(Department.class, Id.class);
        assertEquals(fields.size(), 1);

        fields = EntityUtils.getFieldsByAnnotations(Department.class, Column.class);
        assertEquals(fields.size(), 2);
    }

    @Test
    public void testGetSqlValueMethod() {
        try {
            Object o = EntityUtils.getSqlValue(new Department());
            System.out.println(o.getClass());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
