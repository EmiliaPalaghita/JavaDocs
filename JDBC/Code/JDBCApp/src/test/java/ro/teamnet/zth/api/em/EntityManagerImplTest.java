package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Location;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        location.setLocation(1100L);
        location.setId(1234L);
        location = (Department) entity.insert(location);
        assertNotNull(location);
    }

    @Test
    public void testFindAllMethod() {
        EntityManagerImpl entity = new EntityManagerImpl();
        List<Department> result = entity.findAll(Department.class);
        assertNotEquals(result.size(), 0);
    }

    @Test
    public void testUpdateMethod() {
        EntityManagerImpl entity = new EntityManagerImpl();
        Department department = entity.findById(Department.class, 0L);
        department.setDepartmentName("ce cacat");
        department.setLocation(1600L);
        department = entity.update(department);
        assertNull(department);
        department = entity.findById(Department.class, 271L);
        department.setLocation(1200L);
        department.setDepartmentName("better??");
        department = entity.update(department);
        assertNotNull(department);
    }

    @Test
    public void testDeleteMethod() {
        EntityManagerImpl entity = new EntityManagerImpl();
        Department department = entity.findById(Department.class, 271L);
        entity.delete(department);
    }

    @Test
    public void testFindByParamsMethod() {
        EntityManagerImpl entity = new EntityManagerImpl();
        Map<String, Object> params = new HashMap<>();
        params.put("location_id", 1300L);
        List<Department> departments = entity.findByParams(Department.class, params);
        assertEquals(departments.size(), 0);
        params.clear();
        params.put("location_id", 1700L);
        departments = entity.findByParams(Department.class, params);
        assertNotEquals(departments.size(), 0);
    }
}
