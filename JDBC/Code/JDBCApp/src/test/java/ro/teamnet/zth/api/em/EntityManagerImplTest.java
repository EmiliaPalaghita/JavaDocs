package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Location;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Emilia.Palaghita on 13-Jul-17.
 */
public class EntityManagerImplTest {

    @Test
    public void testFindByIdMethod() {
        EntityManagerImpl entity = new EntityManagerImpl();
        Department result = entity.findById(Department.class, new Long(10));
        assertNotNull(result);
        result = entity.findById(Department.class, new Long(1));
        Department expected = new Department();
        assertEquals(result, expected);
    }

    @Test
    public void testGetNextIdVal() {
        EntityManagerImpl entity = new EntityManagerImpl();
        String tableName = EntityUtils.getTableName(Department.class);
        List<ColumnInfo> columns = EntityUtils.getColumns(Department.class);
        String idColumn = null;
        for (ColumnInfo c : columns) {
            if (c.isId()) {
                idColumn = c.getDbColumnName();
            }
        }
        Long result = entity.getNextIdVal(tableName, idColumn);
        assertNotNull(result);
    }

    @Test
    public void testInsertMethod() {
        EntityManagerImpl entity = new EntityManagerImpl();
        Department location = new Department();
        location.setDepartmentName("fraierii");
        location.setLocation(new Long(1100));
        location.setId(new Long(1234));
        location = (Department) entity.insert(location);
        assertNotNull(location);
    }

    @Test
    public void testFindAllMethod() {
        EntityManagerImpl entity = new EntityManagerImpl();
        List<Department> result= entity.findAll(Department.class);
        assertNotEquals(result.size(), 0);
    }
}
